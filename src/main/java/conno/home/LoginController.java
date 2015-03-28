package conno.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Anastasya on 28.03.2015.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "login")

    public String login(Principal principal) {
        return principal != null ? "home/login" : "/";
    }
}
