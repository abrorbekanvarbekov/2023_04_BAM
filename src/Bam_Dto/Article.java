package Bam_Dto;

public class Article {
    public int id;
    public String title;
    public String body;
    public String regDate;
    public int userId;

    public Article(int id, String regDate,int userId, String title, String body) {
        this.regDate = regDate;
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
