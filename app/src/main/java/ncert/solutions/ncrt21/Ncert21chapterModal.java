package ncert.solutions.ncrt21;

public class Ncert21chapterModal {
    private String chapterName;
    private String chapterUrl;

    public Ncert21chapterModal() {
    }

    public Ncert21chapterModal(String chapterName, String chapterUrl) {
        this.chapterName = chapterName;
        this.chapterUrl = chapterUrl;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }
}
