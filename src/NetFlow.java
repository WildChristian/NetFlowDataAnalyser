import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetFlow {

    private static int MAX_PACKAGE_SIZE_IN_TIME = 100;
    private static int TIME_FOR_ANALYZE = 1000;
    private static String IGNORE_IP_ADDRESS = "";
    private static String DATA_FILE_PATH = "C:\\Users\\chris\\data.txt";

    public static void main(String[] args) throws IOException, ParseException {
        if (args.length == 4) {
            MAX_PACKAGE_SIZE_IN_TIME = Integer.parseInt(args[0]);
            TIME_FOR_ANALYZE = Integer.parseInt(args[1]);
            IGNORE_IP_ADDRESS = args[2];
            DATA_FILE_PATH = args[3];
        }

        BufferedReader reader =
                new BufferedReader(new FileReader(DATA_FILE_PATH));

        Parser netFlowParser = new Parser(new HashMap<>(), IGNORE_IP_ADDRESS);
        Map<String, ArrayList<Data>> netFlowData = netFlowParser.readAllLines(reader);

        if (netFlowData == null) {
            System.out.println("Es konnten keine Daten gefunden werden");
        } else {
            Analyzer analyzer = new Analyzer(MAX_PACKAGE_SIZE_IN_TIME, TIME_FOR_ANALYZE);
            ArrayList<String> ips = analyzer.analyse(netFlowData);

            Printer printer = new Printer(ips);
            printer.print();
        }
    }
}




