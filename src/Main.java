import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final String alphabetUkr = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,\":-!? ";
    private static String sourceFilePath;
    private static String destinationFilePath;

    public static void main(String[] args) {
        String exit = "exit";
        Scanner scan = new Scanner(System.in);
        boolean cycle = true;
        byte operation = 0;  //1 - encrypt, -1 - decrypt
        int filesProcessed = 0;
        int key;
        boolean cycleMenu = true;


        while (cycleMenu){

            while (cycle) {
                System.out.println("Оберіть дію: 1 - Шифруємо файл, -1 - Дешифруємо (тільки 1 або -1)");
                if (scan.hasNextByte()) {
                    operation = scan.nextByte();
                    if (operation == 1 || operation == -1) {
                        cycle = false;
                    }
                } else {
                    scan.next();
                }
            }
            cycle = true;
            while (cycle) {
                System.out.println("Укажіть шлях до файлу:");
                if (scan.hasNextLine()) {
                    sourceFilePath = scan.nextLine();

                    Path pathFile = Path.of(sourceFilePath);
                    if (!Files.isRegularFile(pathFile)) {
                        System.out.println("Файл не існує, повтори введення");
                    } else {
                        cycle = false;

                        String fileName = pathFile.getFileName().toString();
                        String fileNameWithoutExtension = fileName.substring(0, (fileName.lastIndexOf('.')==-1) ? fileName.length() : fileName.lastIndexOf('.'));
                        String fileNameExtension = fileName.substring((fileName.lastIndexOf('.')==-1) ? "" :  fileName.lastIndexOf('.'));

                        destinationFilePath = pathFile.getParent().toString()+fileNameWithoutExtension+"_"+filesProcessed+fileNameExtension;
                    }
                } else {
                    scan.next();
                }
            }
            cycle = true;
            while (cycle) {
                System.out.println("Укажіть ключ шифрування\/дешифрування(0 то буде виконо метобом брут-форс):");
                if (scan.hasNextByte()) {
                    key = scan.nextByte();
                    cycle = false;
                } else {
                    scan.next();
                }
            }
            filesProcessed ++;


            try (FileReader fileRead = new FileReader("F:\\fileReader.txt");
                 FileWriter filewriter = new FileWriter("F:\\fileReader-2.txt");
                 BufferedReader reader = new BufferedReader(fileRead) ) {

                while (reader.ready()) {
                    String  str = Encrypt_decrypt(reader.readLine(), key, operation);
                    filewriter.write(str);
                }
            } catch (Exception e) {
                System.out.println("Something went wrong : " + e);
            }

        }
    }

    public static String Encrypt_decrypt(String string, int key, byte operation){

        StringBuilder strBuild = new StringBuilder();
        int leng = alphabetUkr.length();

        for(int i=0; i<string.length(); i++)
        {
            char chr = string.charAt(i);
            int idx = alphabetUkr.indexOf(chr);

            if(idx==-1){
                strBuild.append(chr);
            } else{
                int newsymbol = (idx+(operation*key))%leng;
                newsymbol = (newsymbol<0) ? leng+newsymbol : newsymbol;

                strBuild.append(alphabetUkr.charAt(newsymbol));
            }
        }
        return strBuild.toString();
    }
}