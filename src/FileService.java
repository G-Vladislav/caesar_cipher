import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    private String sourceFilePath;
    private String destinationFilePath;

    boolean setSourceFilePath(String sourceFilePath, String currentAction) {
        Path pathFile = Path.of(sourceFilePath);
        if (!Files.isRegularFile(pathFile)) {
            System.out.println("The file does not exist! " + sourceFilePath);
            return false;
        } else {
            this.sourceFilePath = sourceFilePath;
            String fileName = pathFile.getFileName().toString();
            String fileNameWithoutExtension = fileName.substring(0, (fileName.lastIndexOf('.')==-1) ? fileName.length() : fileName.lastIndexOf('.'));
            String fileNameExtension = fileName.substring((fileName.lastIndexOf('.')==-1) ? 0 :  fileName.lastIndexOf('.'));
            destinationFilePath = pathFile.getParent().toString()+"\\"+fileNameWithoutExtension+currentAction+fileNameExtension;
            return true;
        }
    }

    void writeDataToFile(int key, int encrytpDecrypt) {
        if (destinationFilePath.isBlank()){
            System.out.println("File to write not specified!");
            return;
        }
        try (FileReader fileRead = new FileReader(sourceFilePath);
             FileWriter filewriter = new FileWriter(destinationFilePath);
             BufferedReader reader = new BufferedReader(fileRead)) {

            CaesarCipher ceasarEncryptDecrypt = new CaesarCipher( 1);
            while (reader.ready()) {
                if (key != 0) {
                    //String str = Encrypt_decrypt(reader.readLine(), key, operation);
                    String str = ceasarEncryptDecrypt.encryptDecrypt(reader.readLine(), key, encrytpDecrypt);
                    filewriter.write(str);
                } else {
                    filewriter.write(ceasarEncryptDecrypt.bruteForce(reader.readLine()));
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong : " + e);
        }
    }
}
