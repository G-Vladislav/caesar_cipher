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

        if (args.length>0) {



        } else {

            Runner runner = new Runner();
            runner.RunCLIInterface();
        }


    }

}