
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailMerge {

    public MailMerge() {

    }

    // For data, read the file and output the lines to a List of (first_name,
    // last_name, address, years)
    // Create a list of lists to store them

    public List<List<String>> readCSV(String dataCSV) {

        List<List<String>> detailList = new ArrayList<List<String>>();

        try {

            // Read the file
            FileReader fr = new FileReader(dataCSV);
            BufferedReader bfc = new BufferedReader(fr);

            String line;

            while (null != (line = bfc.readLine())) {

                List<String> splitString = Arrays.asList(line.split(","));
                detailList.add(splitString);

            }

            detailList.remove(0);
            // System.out.println(detailList);

            bfc.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return detailList;

    }

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

    public void combineFiles(List<List<String>> nameList, String templateLine) {

        for (List<String> detail : nameList) {

            // System.out.println(templateLine);

            String formattedEmail = templateLine.replace("<<address>>", detail.get(2) + "\n\n")
                    .replace("<<first_name>>,", detail.get(0) + "," + "\n\n")
                    .replace("<<years>>", detail.get(3)).replace("\\n", System.lineSeparator()) + "\n";

            System.out.println(formattedEmail);

        }
    }

    // For the file format
    // Read the file and store them in a line with \n

    // Print out the formatted String

}
