package org.springframework.samples.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author
 */
@Controller
@RequestMapping("/views")
public class UploadController {
    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, @RequestParam(value = "description",required = false) String description,
                         @RequestParam(value = "file",required = false) MultipartFile file) throws Exception{
        System.out.println(description);
        if(!file.isEmpty()){
            String path = request.getServletContext().getRealPath("/images/");
            String filename=file.getOriginalFilename();
            File filePath= new File(path,filename);
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + filename));
            return "views/success";
        }else {
            return "views/error";
        }
    }
    @RequestMapping("/xx")
    public String xxx(){
        return "views/success";
    }
}
