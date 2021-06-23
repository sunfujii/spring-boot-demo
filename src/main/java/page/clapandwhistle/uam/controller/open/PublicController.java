package page.clapandwhistle.uam.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicController {
    public static final String URL_PATH_PREFIX = "open";
    public static final String URL_PATH_INDEX = "/index";
    private final String TEMPLATE_PATH_PREFIX = "open/public/";

    @RequestMapping(URL_PATH_PREFIX + URL_PATH_INDEX)
    public String index() {
        return TEMPLATE_PATH_PREFIX + "index";
    }

}
