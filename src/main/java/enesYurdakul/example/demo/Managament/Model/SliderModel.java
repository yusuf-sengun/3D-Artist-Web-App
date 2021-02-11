package enesYurdakul.example.demo.Managament.Model;

import java.sql.Blob;

public class SliderModel {
    private int image_id;
    private Blob image;
    private String image_name;


    public SliderModel(Blob image, String image_name) {
        this.image = image;
        this.image_name = image_name;
    }

    public SliderModel(){}

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
