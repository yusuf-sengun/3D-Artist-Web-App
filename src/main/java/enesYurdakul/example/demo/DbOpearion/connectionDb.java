package enesYurdakul.example.demo.DbOpearion;

import enesYurdakul.example.demo.Managament.Model.*;
import org.springframework.context.annotation.Configuration;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class connectionDb {

    public static java.sql.Connection getConnection() throws Exception {
        try{
            java.lang.String driver = "com.mysql.jdbc.Driver";
            java.lang.String url="jdbc:mysql://localhost:3306/enesyurdakul";
            java.lang.String username ="root";
            java.lang.String password ="1903Yavrukartal.";
            Class.forName(driver);

            java.sql.Connection conn= DriverManager.getConnection(url,username,password);
            System.out.println("Connected..");
            return conn;
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
            System.out.println("exit..");
        }
        return null;
    }
    public static void createSliderTable() throws Exception {
        try{
            java.sql.Connection con = connectionDb.getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS sliders(image_id int NOT NULL AUTO_INCREMENT,image longblob not null ,image_name varchar (50) not null,PRIMARY KEY(image_id))");
            create.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        finally
        {
            System.out.println("Function complete.");
        };
    }
    public static void createProjectTable() throws Exception {
        try{
            java.sql.Connection con = connectionDb.getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS projects(project_id int NOT NULL AUTO_INCREMENT,image longblob not null ,project_name varchar (30) not null,project_client varchar(30),project_technique varchar(30),project_photographer varchar(30),project_agency varchar(30),PRIMARY KEY(project_id))");
            create.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        finally
        {
            System.out.println("Function complete.");
        };
    }
    public static void createUserTable() throws Exception {
        try{
            java.sql.Connection con = connectionDb.getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT,username varchar(50) not null,password varchar (50) not null,roles varchar(10),PRIMARY KEY (id))");
            create.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        finally
        {
            System.out.println("Function complete.");
        };
    }
    public static void createMessageTable() throws  Exception{
        try{
            java.sql.Connection con = connectionDb.getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS messages(id int NOT NULL AUTO_INCREMENT,name varchar(50) not null,email varchar(30) not null,message varchar(255),PRIMARY KEY (id))");
            create.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Function Complete.");
        }

    }
    public static boolean checkUser(UserModel user) {
        try(Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("SELECT password,username FROM users WHERE username=? AND password=?");
            post.setString(1,user.getUsername());
            post.setString(2,user.getPassword());
            ResultSet resultSet= post.executeQuery();
            if(resultSet.next()) {
                    return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<byte[]> getSliders() {
        List<byte[]> arrays = new ArrayList<>();
        try(Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("SELECT image,image_name FROM sliders");
            ResultSet resultSet= post.executeQuery();
            while(resultSet.next()) {
                String fileName= resultSet.getNString("image_name");
                try(FileOutputStream fos = new FileOutputStream(fileName)){
                    byte[] buf=null;
                    Blob blob = resultSet.getBlob("image");
                    int len = (int) blob.length();
                    buf = blob.getBytes(1, len);
                    arrays.add(buf);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrays;
    }
    public static ArrayList<SliderModel> getSliderModels() throws Exception {

        ArrayList<SliderModel> list = new ArrayList<>();
        try (Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("SELECT image_id,image,image_name FROM sliders");
            ResultSet resultSet = post.executeQuery();
            while (resultSet.next()) {
                SliderModel sliderModel = new SliderModel();
                sliderModel.setImage_id(resultSet.getInt("image_id"));
                sliderModel.setImage(resultSet.getBlob("image"));
                sliderModel.setImage_name(resultSet.getString("image_name"));
                list.add(sliderModel);
            }
        }
    return list;
    }
    public static SliderModel getSliderModelWithId(int id) throws Exception {

        SliderModel sliderModel = new SliderModel();
        try (Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("SELECT image_id,image,image_name FROM sliders WHERE image_id=?");
            post.setInt(1,id);
            ResultSet resultSet = post.executeQuery();
            while (resultSet.next()) {
                sliderModel.setImage_id(resultSet.getInt("image_id"));
                sliderModel.setImage(resultSet.getBlob("image"));
                sliderModel.setImage_name(resultSet.getString("image_name"));

            }
        }
        return sliderModel;
    }
    public static void deleteSliderModelWithId(int id) throws Exception {

        SliderModel sliderModel = new SliderModel();
        try (Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("DELETE  FROM sliders WHERE image_id=?");
            post.setInt(1,id);
            post.executeUpdate();
        }
    }
    public static void addSlider(SliderModel sliderModel) {
        try(Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("INSERT INTO sliders(image,image_name) VALUES (?,?)");
            post.setBlob(1,sliderModel.getImage());
            post.setString(2,sliderModel.getImage_name());
            post.executeUpdate();

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
    public static void addProject(ProjectModel projectModel) {
        try(Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("INSERT INTO projects(image,project_name,project_client,project_technique,project_photographer,project_agency) VALUES (?,?,?,?,?,?)");
            post.setBlob(1,projectModel.getImage());
            post.setString(2,projectModel.getProject_name());
            post.setString(3,projectModel.getProject_client());
            post.setString(4,projectModel.getProject_technique());
            post.setString(5,projectModel.getProject_photographer());
            post.setString(6, projectModel.getProject_agency());
            post.executeUpdate();

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
    public static ArrayList<ProjectModel> getProjects() throws Exception {

        ArrayList<ProjectModel> list = new ArrayList<>();
        try (Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("SELECT * FROM projects");
            ResultSet resultSet = post.executeQuery();
            while (resultSet.next()) {
                ProjectModel projectModel = new ProjectModel();
                projectModel.setProject_id(resultSet.getInt("project_id"));
                projectModel.setImage(resultSet.getBlob("image"));
                projectModel.setProject_name(resultSet.getString("project_name"));
                projectModel.setProject_client(resultSet.getString("project_client"));
                projectModel.setProject_technique(resultSet.getString("project_technique"));
                projectModel.setProject_photographer(resultSet.getString("project_photographer"));
                projectModel.setProject_agency(resultSet.getString("project_agency"));
                list.add(projectModel);
            }
        }
        return list;
    }
    public static ProjectModel getProject(int id)throws Exception{
        ProjectModel projectModel = new ProjectModel();

        try(Connection con = connectionDb.getConnection())
        {
            PreparedStatement get = con.prepareStatement("SELECT * FROM projects WHERE project_id=?");
            get.setInt(1,id);
            ResultSet resultSet = get.executeQuery();
            while (resultSet.next())
            {
                projectModel.setProject_id(resultSet.getInt("project_id"));
                projectModel.setProject_name(resultSet.getString("project_name"));
                projectModel.setProject_client(resultSet.getString("project_client"));
                projectModel.setProject_technique(resultSet.getString("project_technique"));
                projectModel.setProject_photographer(resultSet.getString("project_photographer"));
                projectModel.setProject_agency(resultSet.getString("project_agency"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Function Completed.");
        }

        return projectModel;
    }
    public static String getProjectNameWithId(int id) throws Exception {
        String project_name = null;
        try (Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("SELECT project_name FROM projects WHERE project_id=?");
            post.setInt(1,id);
            ResultSet resultSet = post.executeQuery();
            while (resultSet.next()) {
             project_name = resultSet.getString("project_name");
            }
        }
        return project_name;
    }
    public static void deleteProjectModelWithId(int id) throws Exception {
        try (Connection con = connectionDb.getConnection()) {
            PreparedStatement post = con.prepareStatement("DELETE  FROM projects WHERE project_id=?");
            post.setInt(1,id);
            post.executeUpdate();
        }
    }
    public static void sendMessage(MessageModel messageModel) throws Exception{

    try(Connection con= connectionDb.getConnection()){
        PreparedStatement post = con.prepareStatement("INSERT INTO messages(name,email,message) VALUES (?,?,?)");
        post.setString(1,messageModel.getName());
        post.setString(2,messageModel.getEmail());
        post.setString(3,messageModel.getMessage());
        post.executeUpdate();

    }
        catch(Exception e) {
        System.out.println(e.getMessage());
    }
    }
    public static ArrayList<MessageModel> getMessages(){
        ArrayList<MessageModel> messageModels = new ArrayList<>();

        try(Connection con = connectionDb.getConnection())
        {
            PreparedStatement get = con.prepareStatement("SELECT * FROM messages");
            ResultSet resultSet = get.executeQuery();
            while(resultSet.next()) {
                MessageModel messageModel = new MessageModel();
                messageModel.setId(resultSet.getInt("id"));
                messageModel.setName(resultSet.getString("name"));
                messageModel.setEmail(resultSet.getString("email"));
                messageModel.setMessage(resultSet.getString("message"));
                messageModels.add(messageModel);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return messageModels;
    }
    public static void deleteMessage(int id) {
        try(Connection con = connectionDb.getConnection())
        {
            PreparedStatement delete = con.prepareStatement("DELETE FROM messages WHERE id=?");
            delete.setInt(1,id);
            delete.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
