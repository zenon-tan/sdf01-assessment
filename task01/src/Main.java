


import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String csvFile = args[0];
        String templateFile = args[1];

        MailMerge mailMerge = new MailMerge();

        HashMap<String, List<String>> nameList = mailMerge.readCSV(csvFile);
        String mailFormat = mailMerge.readTxt(templateFile);
        mailMerge.combineFiles(nameList, mailFormat);

    }
    
}
