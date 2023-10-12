public class Runner {

    CLI cli = new CLI();
    void RunCLIInterface(){

        int cycleMenu = 0;

        while (cycleMenu>=0) {
            cli.mainMenu();
            cycleMenu = cli.returnIntEnteredValue(false);
            //cli.clearCashScanner();

            switch (cycleMenu){
                case 1 -> {
                    runEncryptCLI();
                }
                case 2 -> {
                    runDecryptCLI(false);
                }
                case 3 -> {
                    runDecryptCLI(true);
                }
                case 4 -> {
                    cycleMenu = -1;
                }
            }
        }
        cli.shutDown();
    }

    void runEncryptCLI(){
        runEncrypt(cli.menuInputPathFile(), cli.returnIntEnteredValue(true));
   }

    void runDecryptCLI(boolean brutForce){
        runDecrypt(cli.menuInputPathFile(), cli.returnIntEnteredValue(true), brutForce);
    }


    void runEncrypt(String inputFilePath, int keyValue){
        FileService fileService = new FileService();
        boolean statusFile = fileService.setSourceFilePath(inputFilePath,"[ENCRYPT]");
        if (statusFile) {
            fileService.writeDataToFile(keyValue,1);
        }
    }

    void runDecrypt(String inputFilePath, int keyValue, boolean brutForce){
        FileService fileService = new FileService();
        boolean statusFile = fileService.setSourceFilePath(inputFilePath, (brutForce) ? "[BRUTE_FORCE]" : "[DECRYPT]");
        if (statusFile) {
            fileService.writeDataToFile((brutForce) ? 0 : keyValue, -1);
//            if (brutForce) {
//                fileService.writeDataToFile(0,-1);
//            } else {
//                fileService.writeDataToFile(keyValue,-1);
//            }
        }
    }

}
