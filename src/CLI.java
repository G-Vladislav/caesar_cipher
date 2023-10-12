import java.util.Scanner;

public class CLI {

     Scanner scan = new Scanner(System.in);
     void mainMenu(){
          System.out.println("Encryption (decryption) using the Caesar method. Choose an action:");
          System.out.println(" 1 - Encryption file");
          System.out.println(" 2 - Decryption file");
          System.out.println(" 3 - Decryption file(then brute force will be performed)");
          System.out.println(" -1 - Exit");
     }

     int returnIntEnteredValue(boolean askKey){
          if (askKey) { System.out.println("Specify the encryption (decryption) key:");}
          if (scan.hasNextInt()) {
               int tempValue = scan.nextInt();
               scan.nextLine();
               return tempValue;
          } else {
               System.out.println("Incorrect value! Could be a number.");
               scan.nextLine();
               return returnIntEnteredValue(askKey);
          }
     }
     String menuInputPathFile(){
          System.out.println("Specify the path to the file: ");
          return scan.nextLine();
     }

     void shutDown() {
          System.out.println("Exit the program");
     }
     void clearCashScanner(){
          scan.nextLine();
     }

}
