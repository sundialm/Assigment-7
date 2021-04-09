package kz.iitu.DB;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

public class Comment {

    private Long id;
    private User author;
    private String content;
    private Timestamp comment_date;

    public String get_comment_date_string(){
        LocalDate localDate = comment_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Month.of(localDate.getMonthValue()) + " " + localDate.getDayOfMonth() + ", " + localDate.getYear();
    }

    public Comment() {
    }

    public Comment(Long id, User author, String content, Timestamp post_date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.comment_date = post_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getComment_date() {
        return comment_date;
    }

    public void setComment_date(Timestamp comment_date) {
        this.comment_date = comment_date;
    }
}
