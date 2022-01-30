import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Printer {

    private final ArrayList<String> ips;

    public Printer(ArrayList<String> ips) {
        this.ips = ips;
    }

    public void print() {
        try {
            FileWriter myWriter = new FileWriter("suspendedIps.txt");

            for (String ip : ips) {
                myWriter.write(ip + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
