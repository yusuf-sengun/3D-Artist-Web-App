package enesYurdakul.example.demo.Managament;

import enesYurdakul.example.demo.DbOpearion.connectionDb;
import enesYurdakul.example.demo.Managament.Model.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class projectsController {


    @GetMapping("/Management/Projects")
    public String getProjects(Model model) throws Exception {
        ArrayList<ProjectModel> list = connectionDb.getProjects();
        model.addAttribute("list",list);
        return "/Management/projects";
    }
    @RequestMapping(value = "/Management/PostProject",method = RequestMethod.POST)
    public String postProjects(@RequestParam(value = "project_name",required = false) String project_name,
                               @RequestParam(value = "image", required = false) MultipartFile image,
                               @RequestParam(value = "project_client",required = false) String project_client,
                               @RequestParam(value = "project_technique",required = false)String project_technique,
                               @RequestParam(value = "project_photographer",required = false)String project_photographer,
                               @RequestParam(value = "project_agency",required = false)String project_agency, Model model) throws Exception {

        ProjectModel projectModel = new ProjectModel(new SerialBlob(image.getBytes()),project_name,project_client,project_technique,project_photographer,project_agency);

        connectionDb.addProject(projectModel);

        ArrayList<ProjectModel> list = connectionDb.getProjects();
        model.addAttribute("list",list);
        return "/Management/projects";
    }
    @GetMapping("/Management/DeleteProject")
    public String deleteProject(@RequestParam(value = "id", required = false) Integer id, Model model) throws Exception {

        String project_name= connectionDb.getProjectNameWithId(id);
        connectionDb.deleteProjectModelWithId(id);

        Path imagesPath = Paths.get("C:\\Users\\Yusuf\\Desktop\\Yusuf\\Enes Yurdakul\\demo\\src\\main\\resources\\static\\projects\\"+project_name+".jpg");

        Files.delete(imagesPath);
        System.out.println("File "+ imagesPath.toAbsolutePath().toString() + " successfully removed");

        ArrayList<ProjectModel> list = connectionDb.getProjects();
        model.addAttribute("list",list);
        return "/Management/projects";
    }
    @GetMapping("/Management/SaveProjects")
    public String saveProject(Model model) throws Exception {
        ArrayList<ProjectModel> list = connectionDb.getProjects();

        for(int i=0;i<list.size();i++)
        {
            byte[] bytes = new byte[0];
            ProjectModel projectModel;
            projectModel = list.get(i);

                bytes=projectModel.getImage().getBytes(1, (int) projectModel.getImage().length());


            BufferedImage image= ImageIO.read(new ByteArrayInputStream(bytes));

            ImageIO.write(image, "jpg", new File("C:\\Users\\Yusuf\\Desktop\\Yusuf\\Enes Yurdakul\\demo\\src\\main\\resources\\static\\projects\\"+projectModel.getProject_name()+".jpg"));

        }
        ArrayList<ProjectModel> list2 = connectionDb.getProjects();
        model.addAttribute("list",list2);
        return "/Management/projects";
    }



}
