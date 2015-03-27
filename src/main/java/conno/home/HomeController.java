package conno.home;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("session")
public class HomeController {





    /*    WebContext ctx =
                new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("today", dateFormat.format(cal.getTime()));

       process("home", ctx, response.getWriter());*/


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {

        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    //public S

}
