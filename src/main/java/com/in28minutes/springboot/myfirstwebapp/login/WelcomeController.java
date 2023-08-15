package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
    
    // private AuthenticationService authenticationService;

    // public LoginController(AuthenticationService authenticationService){
    //     super();
    //     this.authenticationService = authenticationService;
    // }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotWelcomePage(ModelMap model){
        model.put("name", getLoggedinUsername());
        return "welcome"; 
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    // @RequestMapping(value = "login", method = RequestMethod.POST)
    // public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model){
        
    //     // Authentication
    //     // name - in28minutes
    //     // password - dummy

    //     if(authenticationService.authenticate(name, password)){
    //         model.put("name", name);
    //         return "welcome";
    //     }

    //     model.put("errorMessage", "Invalid Credential, Please Try again!");
    //     return "login";
    // }
}
