import java.util.ArrayList;
import java.util.Arrays;

public class CaesarCipher {

    private String currentAlphabet = "";
    private ArrayList<String> currentWords ;
    private static final String ALPHABETUKR = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,\":-!? ";
    private static final String ALPHABETEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,\":-!? ";
    private static final ArrayList<String> WORDSUKR = new ArrayList<>(Arrays.asList("пан","привіт","комп'ютер","телефон","стілець","ліжко","кімната","квартира","привіт",
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

    private static final ArrayList<String> WORDSEN = new ArrayList<>(Arrays.asList("his","that","was","with","they","have","this","from","word",
            "what","some","you","other","were","which","their","time","will","said","each","tell","does","three","want",
            "well","also","play","small","home","read","hand","port","large","spell","even","land","here","must","high","such",
            "follow","why","change","went","light","kind","need","house","picture","again","animal","point","mother","world","near","build","self","earth","father",
            "work","part","take","place","made","live","where","after","back","little","only","round","year","came","show","every",
            "good","give","under","name","very","through","just","form","form","great","think","help","line",
            "differ","turn","cause","much","mean","before","move","right","same","there","when","your",
            "about","many","then","them","write","would","like","these","long","make","thing",
            "look","more","could","come","number","sound","most","people","over","know","water",
            "than","call","first","down","side","been","find","head","stand","page","should","country","found",
            "answer","school","grow","study","still","learn","plant","cover","food","four","between","state",
            "keep","never","last","thought","cross","farm","hard","start","might","story","draw",
            "left","late","don’t","while","press","close","night","real","life","north","book","carry","took","science","room",
            "friend","began","idea","fish","mountain","stop","once","base","hear","horse",
            "sure","watch","colour","face","wood","main","open","seem","together","next","white","children"));

    public CaesarCipher(int ukrEn) {

        if (ukrEn==0) {
            currentAlphabet = ALPHABETUKR;
            currentWords = WORDSUKR;
        } else {
            currentAlphabet = ALPHABETEN;
            currentWords = WORDSEN;
        }
    }

    String encryptDecrypt(String string, int key, int operation){

        StringBuilder strBuild = new StringBuilder();
        int leng = currentAlphabet.length();

        for(int i=0; i<string.length(); i++) {
            char chr = string.charAt(i);
            int idx = currentAlphabet.indexOf(chr);

            if(idx==-1){
                strBuild.append(chr);
            } else{
                int newsymbol = (idx+(operation*key))%leng;
                newsymbol = (newsymbol<0) ? leng+newsymbol : newsymbol;
                strBuild.append(currentAlphabet.charAt(newsymbol));
            }
        }
        return strBuild.toString()+"\n";
    }

    String bruteForce(String string){
        for (byte i=1; i<currentAlphabet.length(); i++){
            String str = encryptDecrypt(string, i, -1);
            String strLow = str.toLowerCase();
            for (int j=0; j<currentWords.size(); j++){
                if (strLow.contains(currentWords.get(j))) {
                    System.out.println("Possible key option: "+i);
                    return str;
                }
            }
        }
        return "";
    }

}
