package abu.sadat.yasin.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

}
