package kz.iitu.DB;

public class Like {
    private Long id;
    private User author;

    public Like(Long id, User author) {
        this.id = id;
        this.author = author;
    }

    public Like(){

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

}
