import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public final static int POSITION_DATE = 0;
    public final static int POSITION_TIME = 1;
    public final static int POSITION_IP_SRC_ADRESS = 2;

    private final String IGNORE_IP_ADDRESS;

    Map<String, ArrayList<Data>> netFlowData;

    public Parser(HashMap<String, ArrayList<Data>> netFlowData, String IGNORE_IP_ADDRESS) {
        this.netFlowData = netFlowData;
        this.IGNORE_IP_ADDRESS = IGNORE_IP_ADDRESS;
    }

    public Map<String, ArrayList<Data>> readAllLines(BufferedReader reader) throws IOException, ParseException {
        String line;
        ArrayList<String[]> dataStrings = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            dataStrings.add(line.split(" "));
        }

        for (String[] dataString : dataStrings) {
            if (!(dataString[POSITION_IP_SRC_ADRESS].equalsIgnoreCase(IGNORE_IP_ADDRESS))) {
                netFlowData.put(dataString[POSITION_IP_SRC_ADRESS], new ArrayList<>());
            }
        }

        if (netFlowData.size() != 0) {
            for (String[] dataString : dataStrings) {
                if (!(dataString[POSITION_IP_SRC_ADRESS].equalsIgnoreCase(IGNORE_IP_ADDRESS))) {
                    netFlowData.get(dataString[POSITION_IP_SRC_ADRESS]).add(new Data(
                            new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS").parse(dataString[POSITION_DATE] + " " + dataString[POSITION_TIME]), null, dataString[POSITION_IP_SRC_ADRESS], null, 0));
                }
            }        } else {
            return null;
        }


        return netFlowData;
    }
}