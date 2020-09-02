package tech.codingclub.helix.controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by akash.
 */
public class BaseController {
    @ExceptionHandler
    public
    String defaultErrorHandler(HttpServletRequest req,  Exception e) throws Exception {
       // modelmap.addAttribute("MESSAGE","Something went wrong!")
        return "alien";
    }
}
