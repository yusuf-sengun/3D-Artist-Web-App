package enesYurdakul.example.demo.Controller;


import enesYurdakul.example.demo.DbOpearion.connectionDb;
import enesYurdakul.example.demo.Managament.Model.MessageModel;
import enesYurdakul.example.demo.Managament.Model.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.*;
import java.util.*;


@Controller
public class WebController {
    @GetMapping(value = "/")
    public String getMain(Model model) {
        File f = new File("C:\\Users\\Yusuf\\Desktop\\Yusuf\\Enes Yurdakul\\demo\\src\\main\\resources\\static\\sliders\\");
        ArrayList<String> list = new ArrayList<>();
        for(File file : f.listFiles())
        {
            if(file.isFile()) {
                list.add(file.getName());
            }
        }
        model.addAttribute("list",list);
        return "Home";
    }
    @GetMapping(value = "/Projects")
    public String getProject(Model model) throws Exception {

        ArrayList<ProjectModel> list = connectionDb.getProjects();
        model.addAttribute("list",list);
        return "Projects";
    }
    @GetMapping("/Project")
    public String openProject(@RequestParam(value = "id",required = false) int id, Model model) throws Exception {

        ProjectModel projectModel =  connectionDb.getProject(id);
        model.addAttribute("item",projectModel);
        return "Project";
    }
    @GetMapping(value = "/About")
    public String getAbout()  {

        return "About";
    }
    @GetMapping(value = "/Contact")
    public String getContact() {
        return "Contact";
    }
    @RequestMapping(value = "/Contact/Sendemail")
    public String sendEmail(@RequestParam(value = "name", required = false)String name,
                          @RequestParam(value = "email", required = false)String email,
                           @RequestParam(value = "message", required = false)String message) throws Exception {
        MessageModel messageModel = new MessageModel(name,email,message);
        connectionDb.sendMessage(messageModel);
        return "Contact";
    }

}
