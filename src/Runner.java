public class Runner {

    CLI cli = new CLI();
    void RunCLIInterface(){

        int cycleMenu = 0;

        while (cycleMenu>=0) {
            cli.mainMenu();
            cycleMenu = cli.returnIntEnteredValue();
            //cli.clearCashScanner();

            switch (cycleMenu){
                case 1 -> {
                    runEncrypt();
                }
                case 2 -> {
                    runDecrypt(false);
                }
                case 3 -> {
                    runDecrypt(true);
                }
                case 4 -> {
                    cycleMenu = -1;
                }
            }
        }
        cli.shutDown();
    }

    void runEncrypt(){
        FileService fileService = new FileService();
        boolean statusFile = fileService.setSourceFilePath(cli.menuInputPathFile(),"[ENCRYPT]");
        if (statusFile) {
            cli.menuCiphKey();
            fileService.writeDataToFile(cli.returnIntEnteredValue(),1);
        }

    }

    void runDecrypt(boolean brutForce){
        FileService fileService = new FileService();
        boolean statusFile = fileService.setSourceFilePath(cli.menuInputPathFile(),"[DECRYPT]");
        if (statusFile) {
            if (brutForce) {
                fileService.writeDataToFile(0,-1);
            } else {
                cli.menuCiphKey();
                fileService.writeDataToFile(cli.returnIntEnteredValue(),-1);
            }
        }
    }

}
