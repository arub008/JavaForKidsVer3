package conno.lesson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anastasya on 30.03.2015.
 */
@Controller
public class Level1Controller {

    @RequestMapping(value = "lesson/level1", method = RequestMethod.GET)
    public String level1Load() {
        return "lesson/level1";
    }
}
