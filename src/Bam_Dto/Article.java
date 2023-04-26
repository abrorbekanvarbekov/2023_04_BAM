package Bam_Dto;

public class Article {
    public int id;
    public String title;
    public String body;
    public String regDate;

//    public Article(int id, String dateStr, String title, String body) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM월 dd날 HH시 mm분 ss초");
//        Date now = new Date();
//        regDate = formatter.format(now);
//    }

    public Article(int id, String regDate, String title, String body) {
//        this(id, Util.getDateStr(), title, body);
        this.regDate = regDate;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
