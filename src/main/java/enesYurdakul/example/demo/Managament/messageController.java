package enesYurdakul.example.demo.Managament;


import enesYurdakul.example.demo.DbOpearion.connectionDb;
import enesYurdakul.example.demo.Managament.Model.MessageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class messageController {

    @GetMapping("/Management/Messages")
    public String getMessages(Model model)
    {
        ArrayList<MessageModel> messageModels;
        messageModels = connectionDb.getMessages();
        model.addAttribute("list",messageModels);
        return "/Management/messages";
    }
    @RequestMapping("/Management/DeleteMessage")
    public String deleteMessage(@RequestParam("id") int id,Model model)
    {
        ArrayList<MessageModel> messageModels;
        connectionDb.deleteMessage(id);
        messageModels = connectionDb.getMessages();
        model.addAttribute("list",messageModels);
        return "/Management/messages";

    }

}
