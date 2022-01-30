import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

class NetFlowTest {

    @ParameterizedTest
    @CsvSource({"100,1000,'',C:\\Users\\chris\\data.txt,./suspendedIps.txt,./src/Test/expected/Test01.txt",
            "10000,1000,'',C:\\Users\\chris\\data.txt,./suspendedIps.txt,./src/Test/expected/Test02.txt",
            "100,3,'',C:\\Users\\chris\\data.txt,./suspendedIps.txt,./src/Test/expected/Test03.txt"})
    void main(String packetsize, String milliseconds, String ip, String datapath, String resultDataPath, String expectedDataPath) throws IOException, ParseException {
        String[] array = {packetsize, milliseconds, ip, datapath};


        NetFlow.main(array);

        ArrayList<String> resultIps = readFile(resultDataPath);
        ArrayList<String> expectedIps = readFile(expectedDataPath);

        Assertions.assertTrue(compareResultWithExpected(resultIps, expectedIps));
    }

    private ArrayList<String> readFile(String path) throws IOException {

        BufferedReader reader =
                new BufferedReader(new FileReader(path));
        String line;
        ArrayList<String> dataStrings = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            dataStrings.add(line);
        }
        return dataStrings;
    }

    private boolean compareResultWithExpected(ArrayList<String> resultIps, ArrayList<String> expectedIps) {
        if (resultIps.size() == 0 && expectedIps.size() == 0) {
            return true;
        } else {
            for (int i = 0; i < resultIps.size(); ++i) {
                if (!(resultIps.get(i).equalsIgnoreCase(expectedIps.get(i)))) {
                    return false;
                }
            }
            return true;
        }

    }
}