package enesYurdakul.example.demo.Managament;

import enesYurdakul.example.demo.DbOpearion.connectionDb;
import enesYurdakul.example.demo.Managament.Model.SliderModel;
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
public class sliderController {

    @GetMapping("/Management/Sliders")
    public String getSliders(Model model) throws Exception {
        ArrayList<SliderModel> list ;
        list = connectionDb.getSliderModels();
        model.addAttribute("list",list);
        return "/Management/sliders";
    }
    @RequestMapping(value = "/Management/PostSlider",method = RequestMethod.POST)
    public String postSlider(@RequestParam(value = "image", required = false) MultipartFile image, @RequestParam(value = "image_name" ,required = false)String image_name,Model model) throws Exception {

        SliderModel sliderModel= new SliderModel(new SerialBlob(image.getBytes()),image_name);
        connectionDb.addSlider(sliderModel);

        ArrayList<SliderModel> list =  connectionDb.getSliderModels();
        model.addAttribute("list",list);
        return "/Management/sliders";
    }
    @GetMapping("/Management/DeleteSlider")
    public String deleteSlider(@RequestParam(value = "id", required = false) Integer id, Model model) throws Exception {

        SliderModel sliderModel =  connectionDb.getSliderModelWithId(id);
        connectionDb.deleteSliderModelWithId(id);

        Path imagesPath = Paths.get("C:\\Users\\Yusuf\\Desktop\\Yusuf\\Enes Yurdakul\\demo\\src\\main\\resources\\static\\sliders\\"+sliderModel.getImage_name()+".jpg");

            Files.delete(imagesPath);
            System.out.println("File "+ imagesPath.toAbsolutePath().toString()+ " successfully removed");

        ArrayList<SliderModel> list = connectionDb.getSliderModels();

        model.addAttribute("list",list);
        return "/Management/sliders";
    }
    @GetMapping("/Management/SaveSliders")
    public String saveSlider(Model model) throws Exception {

        ArrayList<SliderModel> list = connectionDb.getSliderModels();

        for(int i=0;i<list.size();i++)
        {
            byte[] bytes;
            SliderModel sliderModel;
            sliderModel = list.get(i);

            bytes=sliderModel.getImage().getBytes(1, (int) sliderModel.getImage().length());

            BufferedImage image= ImageIO.read(new ByteArrayInputStream(bytes));

            ImageIO.write(image, "jpg", new File("C:\\Users\\Yusuf\\Desktop\\Yusuf\\Enes Yurdakul\\demo\\src\\main\\resources\\static\\sliders\\"+sliderModel.getImage_name()+".jpg"));

        }

        ArrayList<SliderModel> list2 =  connectionDb.getSliderModels();
        model.addAttribute("list",list2);
        return "/Management/sliders";
    }
}
