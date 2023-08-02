import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String alphabetUkr = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,\":-!? ";

    private static final ArrayList<String> words = new ArrayList<>(Arrays.asList("пан","привіт","комп'ютер","телефон","стілець","ліжко","кімната","квартира","привіт",
            "будинок","магазин","ресторан","музей","парк","дорога","село","місто","університет","школа","дякую","будь ласка","так","добре","побачення",
            "справи","зват","мене","також","дуже","велике","маленьке","більше","менше","день","рік","місяць","тиждень","хліб","їхнь","країнсь",
            "вода","сонце","місяць","зірка","небо","дерево","квітка","мова","книга","ниця","енка","еньк","льно","вати","ання","ення","ість","ечек","граф",
            "вітаю","невідомий","неділя","недовго","недоро","незабаром","немає","необхід","ніч","ночі","обіцят","обрати","ходжен","правд",
            "оголошення","одяг","озеро","око","олівець","осінь","особливо","отець","очі","палець","парк","парта","пенал","пензлик","передача",
            "переклад","перекладач","перша","перший","підлог","підніматися","після","пісня","піцца","плащ","плітка","справжнь","погано",
            "погода","подорож","покоївка","поле","помідор","пора","поруч","поспішати","постачати","починаючи","офіцій","докумен",
            "почуття","працювати","прекрасний","приймати","примар","принести","принц","програма","важна","кільк","радикаль",
            "проїхати","прочитати","птиця","пустеля","путівник","пучок","п'ятниця","ревнивий","робити","родич","пропонов",
            "рослина","рука","рух","ручка","ручник","сад","садити","сам","самка","саме","самий","самі","відчутт",
            "сарай","свій","світло","секрет","сестра","сім","сім'я","слуха","слідкувати","слово","сніг","правоп",
            "сніданок","собака","сон","сонце","сосна","спати","спина","сподіватися","тобі","товариш","той",
            "спорт","справж","справ","сприя","стадо","стакан","старий","стіл","сторона","стояти","студент","сукня","сумка","сумн","сусід",
            "сутінки","сухий","сходити","схожий","сцена","сьогодні","сюжет","сюрприз","так","таксі",
            "там","танок","тата","тварина","телефон","темний","термін","тесляр","тетя","тиждень","тижня","тиран"));
    private static String sourceFilePath;
    private static String destinationFilePath;

    public static void main(String[] args) {
        String exit = "exit";
        Scanner scan = new Scanner(System.in);
        boolean cycle;
        byte operation = 0;  //1 - encrypt, -1 - decrypt
        int filesProcessed = 0;
        byte key = 0;
        boolean cycleMenu = true;

        while (cycleMenu){

            cycle = true;
            while (cycle) {
                System.out.println("Оберіть дію: 1 - Шифруємо файл, -1 - Дешифруємо (тільки 1 або -1)");
                if (scan.hasNextByte()) {
                    operation = scan.nextByte();
                    scan.nextLine();
                    if (operation == 1 || operation == -1) {
                        cycle = false;
                    }
                } else {
                    scan.nextLine();
                }
            }
            cycle = true;
            while (cycle) {
                System.out.println("Укажіть шлях до файлу:");
                sourceFilePath = scan.nextLine();
                Path pathFile = Path.of(sourceFilePath);
                if (!Files.isRegularFile(pathFile)) {
                    System.out.println("Файл не існує, повтори введення");
                } else {
                        cycle = false;

                        String fileName = pathFile.getFileName().toString();
                        String fileNameWithoutExtension = fileName.substring(0, (fileName.lastIndexOf('.')==-1) ? fileName.length() : fileName.lastIndexOf('.'));
                        String fileNameExtension = fileName.substring((fileName.lastIndexOf('.')==-1) ? 0 :  fileName.lastIndexOf('.'));

                        destinationFilePath = pathFile.getParent().toString()+fileNameWithoutExtension+"_"+filesProcessed+fileNameExtension;
                }
            }
            cycle = true;
            while (cycle) {
                System.out.println("Укажіть ключ шифрування/дешифрування(0 то буде виконо метобом брут-форс):");
                if (scan.hasNextByte()) {
                    key = scan.nextByte();
                    scan.nextLine();
                    cycle = false;
                } else {
                    scan.nextLine();
                }
            }
            filesProcessed ++;

            try (FileReader fileRead = new FileReader(sourceFilePath);
                 FileWriter filewriter = new FileWriter(destinationFilePath);
                 BufferedReader reader = new BufferedReader(fileRead) ) {

                if (key!=0) {
                    while (reader.ready()) {
                        String str = Encrypt_decrypt(reader.readLine(), key, operation);
                        filewriter.write(str);
                    }
                } else {
                    while (reader.ready()) {
                        filewriter.write(BruteForce(reader.readLine()));
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong : " + e);
            }
            System.out.println("Для завершення роботи введіть \"exit\" або для повторення операцій");
            String str = scan.nextLine();
            if (str.toLowerCase().equals(exit)) {
                cycleMenu = false;
                scan.close();
            }
        }
    }

    public static String Encrypt_decrypt(String string, byte key, byte operation){

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

    public static String BruteForce(String string){

        for (byte i=1; i<alphabetUkr.length(); i++){
            String str = Encrypt_decrypt(string, i, (byte)-1);
            String strLow = str.toLowerCase();
            if (strLow.contains("! ") || strLow.contains("? ") || strLow.contains(", ") || strLow.contains(". ")){
                for (int j=0; j<words.size(); j++){
                    if (strLow.contains(words.get(j))) {
                        System.out.println("Можливий варіант ключа: "+i);
                        return str;
                    }
                }
            }
        }
        return "";
    }
}