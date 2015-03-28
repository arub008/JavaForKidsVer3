package conno.signin;

import conno.account.Account;
import conno.signup.SignupForm;
import conno.support.web.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SigninController {

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String signin() {
        return "signin/signin";
    }


    //нужно прочитать параметры .findByEmail
 /*   @RequestMapping(value = "signin", method = RequestMethod.POST)
    public String signinLogin(){
        return "home/login";
    }*/

}
