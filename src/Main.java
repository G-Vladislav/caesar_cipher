import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String ENCRYPT = "ENCRYPT";
    private static String DECRYPT = "DECRYPT";
    private static String BRUTEFORCE = "BRUTE_FORCE";


    public static void main(String[] args) {
        Runner runner = new Runner();
        if (args.length>0) {
            String command = args[0].trim();
            String inputPathFile  = args[1].trim();
            boolean bruteForceFlag = BRUTEFORCE.equalsIgnoreCase(command);
            int keyValue = (bruteForceFlag) ? Integer.parseInt(args[2].trim()) : 0;

            if (ENCRYPT.equalsIgnoreCase(command)){
                runner.runEncrypt(inputPathFile, keyValue);
            } else {
                runner.runDecrypt(inputPathFile, keyValue, bruteForceFlag);
            }
        } else {
            runner.RunCLIInterface();
        }
    }
}