import java.util.ArrayList;
import java.util.Map;

public class Analyzer {

    private final int MAX_PACKAGE_SIZE_IN_TIME;
    private final int TIME_FOR_ANALYZE;

    public Analyzer(int MAX_PACKAGE_SIZE_IN_TIME, int TIME_FOR_ANALYZE) {
        this.MAX_PACKAGE_SIZE_IN_TIME = MAX_PACKAGE_SIZE_IN_TIME;
        this.TIME_FOR_ANALYZE = TIME_FOR_ANALYZE;
    }

    public ArrayList<String> analyse(Map<String, ArrayList<Data>> netFlowData) {
        ArrayList<String> listOfIndicatedIps = new ArrayList<>();
        for (var entry : netFlowData.entrySet()) {
            ArrayList<Data> listNetFlowData = entry.getValue();
            String ip = checkDos(listNetFlowData);

            if (!(ip.equalsIgnoreCase(""))) {
                listOfIndicatedIps.add(ip);
            }
        }
        return listOfIndicatedIps;
    }

    public String checkDos(ArrayList<Data> listNetFlowData) {
        int counter = 0;
        for (int i = 0; i <= listNetFlowData.size(); ++i) {
            for (int j = i + 1; j < listNetFlowData.size(); ++j) {
                long a = listNetFlowData.get(j).time.getTime() - listNetFlowData.get(i).time.getTime();
                if ((listNetFlowData.get(j).time.getTime() - listNetFlowData.get(i).time.getTime()) <= TIME_FOR_ANALYZE) {
                    ++counter;
                }
            }
            if (counter >= MAX_PACKAGE_SIZE_IN_TIME) {
                return listNetFlowData.get(0).srcIp;
            }
            counter = 0;
        }
        return "";
    }
}
