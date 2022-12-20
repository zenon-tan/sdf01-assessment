
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MailMerge {

    int numOfPeople = 0;

    public MailMerge() {

    }

    // For data, read the file and output the lines to a List of (first_name,
    // last_name, address, years)
    // Create a nested list to store them

    public HashMap<String, List<String>> readCSV(String dataCSV) {

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
                tempList.add("<<%s>>".formatted(i));
                tempdetailList.add(tempList);
                //System.out.println(tempdetailList);
            }

            //System.out.println(tempdetailList);

            String otherLine;

            while (null != (otherLine = bfc.readLine())) {

               //System.out.println(otherLine);
                String[] splitOther = otherLine.split(",");
            

                for(int i = 0; i < splitOther.length; i++) {
                    tempdetailList.get(i).add(splitOther[i]);
                }

                numOfPeople++;
                //System.out.println(numOfPeople);
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

        for(int i = 1; i <= numOfPeople; i++) {

            String formattedEmail = templateLine;

            // For formatting only
            formattedEmail = formattedEmail.replace("<<last_name>>,", "<<last_name>>" + "," + "\n\n")
            .replace("<<first_name>>,", "<<first_name>>" + "," + "\n")
            .replace("<<price>>!", "<<price>>! " + "\n\n")
            .replace("<<address>>", "<<address>>" + "\n\n") + "\n";

            for(String key : nameList.keySet()) {

                if(templateLine.contains(key)) {

                    formattedEmail = formattedEmail.replace(key, nameList.get(key).get(i))
                                    .replace("\\n", System.lineSeparator());
                }

            }

            System.out.println(formattedEmail);

        }
        
    }

}
