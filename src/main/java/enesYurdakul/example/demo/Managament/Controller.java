package enesYurdakul.example.demo.Managament;

import enesYurdakul.example.demo.DbOpearion.connectionDb;
import enesYurdakul.example.demo.Managament.Model.UserModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value="/Management")
    public String getManagement(Model model)
    {

        model.addAttribute("userModel",new UserModel());
        return "/Management/login";
    }
    @PostMapping("/Management")
    public String getManagementSuccess(@Valid @ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult, Model model, HttpServletResponse response)
    {
        try{
            connectionDb.getConnection();
            connectionDb.createUserTable();
            connectionDb.createSliderTable();
            connectionDb.createProjectTable();
            connectionDb.createMessageTable();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        boolean flag=false;
        UserModel user = new UserModel();
        user.setPassword(userModel.getPassword());
        user.setUsername(userModel.getUsername());
        if(bindingResult.hasErrors())
        {
            return "Management/login";
        }
        try {
            flag = connectionDb.checkUser(user);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(flag) {
            model.addAttribute("user",user.getUsername());
            try {
                response.sendRedirect("/Management/Sliders");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //return "Management/sliders";
        }
        else
        {
            model.addAttribute("error","Kullanıcı adı veya şifre hatalı");
            return "Management/login";
        }
    return null; // burada hata ekranı dön
    }
}
