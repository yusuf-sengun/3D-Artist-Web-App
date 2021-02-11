package enesYurdakul.example.demo.Managament.Model;

import java.sql.Blob;

public class  ProjectModel {

    private int project_id;
    private Blob image;
    private String project_name;
    private String project_client;
    private String project_technique;
    private String project_photographer;
    private String project_agency;

    public ProjectModel(Blob image, String project_name, String project_client, String project_technique, String project_photographer, String project_agency) {
        this.image = image;
        this.project_name = project_name;
        this.project_client = project_client;
        this.project_technique = project_technique;
        this.project_photographer = project_photographer;
        this.project_agency = project_agency;
    }
    public ProjectModel(){}

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_client() {
        return project_client;
    }

    public void setProject_client(String project_client) {
        this.project_client = project_client;
    }

    public String getProject_technique() {
        return project_technique;
    }

    public void setProject_technique(String project_technique) {
        this.project_technique = project_technique;
    }

    public String getProject_photographer() {
        return project_photographer;
    }

    public void setProject_photographer(String project_photographer) {
        this.project_photographer = project_photographer;
    }

    public String getProject_agency() {
        return project_agency;
    }

    public void setProject_agency(String project_agency) {
        this.project_agency = project_agency;
    }
}
