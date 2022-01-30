import java.util.Date;

public class Data {

    Date time;
    String protocol;
    String srcIp;
    String destIp;
    int packetSize;

    public Data(Date time, String protocol, String srcIp, String destIp, int packetSize) {
        this.time = time;
        this.protocol = protocol;
        this.srcIp = srcIp;
        this.destIp = destIp;
        this.packetSize = packetSize;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }
}

