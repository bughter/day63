package com.czy.controller;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/30 17:19
 * @description
 */
@Controller
public class UeditorController {
    @Value("${upload}")
    private String upload;
    @Value("${path}")
    private String path;

    @RequestMapping("doExec")
    @ResponseBody
    public String doExec(String action, HttpServletRequest request, MultipartFile upfile){
        String result = null;
        if("config".equals(action)){
            return new ActionEnter( request, request.getContextPath() ).exec();
        }if("uploadimage".equals(action)){
            result = uploadimage(upfile);

        }
        return result;
    }


    public String uploadimage(MultipartFile upfile){
        JSONObject jsonObject = null;
        String originalFilename = upfile.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.lastIndexOf("."));
        //
        if(!upfile.isEmpty()){
            String fileName = UUID.randomUUID() + type;
            String targetFileName = upload + fileName;
            try {
                upfile.transferTo(new File(targetFileName));
                jsonObject =   new JSONObject(resultMap("SUCCESS",originalFilename,upfile.getSize(),targetFileName,type,path+File.separator+fileName));
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject = new JSONObject(resultMap("FAIL",null,0,null,null,null));
            }
        }
        return jsonObject.toString();
    }


    //{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg","type": ".jpg","url": "/1535961757878095151.jpg"}

    private Map<String,Object> resultMap(String state, String original, long size, String title, String type, String url){
        Map<String ,Object> result = new HashMap<>();
        result.put("state",state);
        result.put("original",original);
        result.put("size",size);
        result.put("title",title);
        result.put("type",type);
        result.put("url", url);
        return result;
    }

}
