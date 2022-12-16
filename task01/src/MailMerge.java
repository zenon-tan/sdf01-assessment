
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MailMerge {

    int sizeOfCSVLines = 0;

    public MailMerge() {

    }

    // For data, read the file and output the lines to a List of (first_name,
    // last_name, address, years)
    // Create a nested list to store them
    // Map? Maybe not

    public HashMap<String, List<String>> readCSV(String dataCSV) {

        // List of HashMap<String, String> of key = first_name, last_name, address, years

        HashMap<String, List<String>> tempHash = new HashMap<>();
        List<List<String>> tempdetailList = new ArrayList<List<String>>();

        try {

            // Read the file
            FileReader fr = new FileReader(dataCSV);
            BufferedReader bfc = new BufferedReader(fr);

            String line = bfc.readLine();

            String[] splitHeaders = line.split(",");
            for(String i : splitHeaders) {
                List<String> tempList = new ArrayList<>();
                tempList.add(i);
                tempdetailList.add(tempList);
            }

            //System.out.println(tempdetailList);

            String otherLine;

            while (null != (otherLine = bfc.readLine())) {

               //System.out.println(otherLine);
                String[] splitOther = otherLine.split(",");
            

                for(int i = 0; i < splitOther.length; i++) {
                    tempdetailList.get(i).add(splitOther[i]);
                }

                sizeOfCSVLines++;
            }

            //System.out.println(tempdetailList);

            for(List<String> i : tempdetailList) {


                tempHash.put(i.get(0), i);

            }
            

            //System.out.println(tempHash);

            bfc.close();
            fr.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempHash;

    }

    // For .txt, read the lines and append it to a Stringbuilder

    public String readTxt(String txt) {

        StringBuilder mailDetail = new StringBuilder();

        try {

            FileReader fr = new FileReader(txt);
            BufferedReader bfc = new BufferedReader(fr);

            String line;

            while (null != (line = bfc.readLine())) {

                mailDetail.append(line);

            }

            bfc.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mailDetail.toString();

    }

    public void combineFiles(HashMap<String, List<String>> nameList, String templateLine) {

        for(int i = 1; i < sizeOfCSVLines; i++) {

            String formattedEMail = templateLine
            .replace("<<address>>", nameList.get("address").get(i) +"\n\n")
            .replace("<<first_name>>,", nameList.get("first_name").get(i) + "," + "\n\n")
            .replace("<<years>>", nameList.get("years").get(i))
            .replace("\\n", System.lineSeparator()) 
                    + "\n";

            System.out.println(formattedEMail);



        }

        
    }

}
