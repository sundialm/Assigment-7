package kz.iitu.DB;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aralasu.kz?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static User getUserByEmailPassword(String email, String password) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ? and password = ?");

            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("picture_url")
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserById(Long id) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE id = ?");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("picture_url")
                );
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean editProfile(Long id, String email, String full_name, java.util.Date birth_date) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  users SET email=?, full_name=?, birth_date=?" +
                    "WHERE id = ?");


            statement.setString(1, email);
            statement.setString(2, full_name);
            Date date = (Date) birth_date;
            statement.setDate(3, date);
            statement.setLong(4, id);

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;

    }

    public static boolean updatePassword(Long id, String password) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  users SET password=?" +
                    "WHERE id = ?");

            statement.setString(1, password);
            statement.setLong(2, id);

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;

    }

    public static boolean updatePicture(Long id, String picture_url) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE  users SET picture_url=?" +
                    "WHERE id = ?");

            statement.setString(1, picture_url);
            statement.setLong(2, id);

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;

    }

    public static boolean addPost(Post post) {
        int rows = 0;


        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO posts (id, author_id, title, short_content, content, post_date) values (null , ?,?,?,?,null )");

            statement.setLong(1, post.getAuthor().getId());
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getShort_content());
            statement.setString(4, post.getContent());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean addComment(Comment comment) {
        int rows = 0;


        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (id, author_id, content, comment_date) values (null , ?,?,null )");

            statement.setLong(1, comment.getAuthor().getId());
            statement.setString(2, comment.getContent());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static ArrayList<Post> allPosts() {
        ArrayList<Post> posts = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM posts");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static ArrayList<Post> getPostsByUserId(Long id) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM posts WHERE author_id =?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static Post getPostById(Long id) {
        Post post = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM posts WHERE id =?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                post = new Post(
                        resultSet.getLong("id"),
                        DBManager.getUserById(resultSet.getLong("author_id")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public static boolean updatePost(Post post) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE posts SET title=?, short_content=?, content=? WHERE id=?");

            statement.setString(1, post.getTitle());
            statement.setString(2, post.getShort_content());
            statement.setString(3, post.getContent());
            statement.setLong(4, post.getId());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean updateComment(Comment comment) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE posts SET  content=? WHERE id=?");

            statement.setString(1, comment.getContent());
            statement.setLong(2, comment.getId());
            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows > 0;
    }

    public static boolean deletePost(Long id) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM posts WHERE id = ?");

            statement.setLong(1, id);

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;

    }

    public static boolean deleteComment(Long id) {
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM posts WHERE id = ?");

            statement.setLong(1, id);

            rows = statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;

    }
}
