package enesYurdakul.example.demo.Managament.Model;

public class MessageModel {
    private int id;
    private String name;
    private String email;
    private String message;


    public MessageModel(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public MessageModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
