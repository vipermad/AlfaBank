package Controllers;

import Parsing.ParserApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String kursValut( Model model) {

        ParserApi.connect();

        model.addAttribute("value", ParserApi.getLatestPrice());
        return "index";
    }
}
