package ImageHoster.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity annotation specifies that the corresponding class is a JPA entity
@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'images'. Hence the table named 'images' will be created in the database with all the columns mapped to all the attributes in 'Image' class
@Table(name = "comment")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;

    // Text is a Postgres specific column type that allows you to save
    // text based data that will be longer than 256 characters
    // this is a base64 encoded version of the image
    @Column(name = "text",columnDefinition = "TEXT")
    private String text;

    @Column(name = "date")
    private Date date;

    //The 'comment' table is mapped to 'users' table with Many:One mapping
    // One user can post multiple comments but one comment should belong to one user
    //FetchType is EAGER
    @ManyToOne(fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'images' table referring the primary key in 'users' table will be 'user_id'
    @JoinColumn(name = "user_id")
    private User user;

    //The 'comment' table is mapped to 'image' table with Many:One mapping
    //One image can have  multiple comments but one comment can only belong to one user
    //FetchType is EAGER
    @ManyToOne(fetch = FetchType.EAGER )
    //Below annotation indicates that the name of the column in 'images' table referring the primary key in 'users' table will be 'user_id'
    @JoinColumn(name = "image_id")
    private Image image;

    public Comment() {
    }

    public Comment(int id, String text,Image imageFile, Date date) {
        this.id = id;
        this.text = text;
        this.image = imageFile;
        this.date = date;
    }

    public Comment(int id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
