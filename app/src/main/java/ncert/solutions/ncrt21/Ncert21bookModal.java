package ncert.solutions.ncrt21;

public class Ncert21bookModal {
    String subId;
    String subName;
    String subNum;

    public Ncert21bookModal() {
    }

    public Ncert21bookModal(String subId, String subName, String subNum) {
        this.subId = subId;
        this.subName = subName;
        this.subNum = subNum;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubNum() {
        return subNum;
    }

    public void setSubNum(String subNum) {
        this.subNum = subNum;
    }
}
