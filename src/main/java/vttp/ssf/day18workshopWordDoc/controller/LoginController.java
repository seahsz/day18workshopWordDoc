package vttp.ssf.day18workshopWordDoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping(path={"/login", "/", "index.html"})
    public ModelAndView getLogin() {

        ModelAndView mav = new ModelAndView("login");

        return mav;
    }

    @PostMapping("/login")
    public ModelAndView postLogin(HttpSession session, @RequestBody MultiValueMap<String, String> form) {

        if (form.getFirst("fullName") == null || form.getFirst("age") == null || form.getFirst("fullName").isBlank()
                || form.getFirst("age").isBlank()) {
            return new ModelAndView("login");
        }

        // Task 6 - Store "full name" and "age" in Httpsession
        session.setAttribute("fullName", form.getFirst("fullName"));
        
        try {
            int age = Integer.parseInt(form.getFirst("age"));
            session.setAttribute("age", age);

            // Task 8 - If below Age 10, send to "underage.html"
            if (age < 10)
                return new ModelAndView("underage");

            
        } catch (NumberFormatException e) {
            return new ModelAndView("login");
        }

        ModelAndView mav = new ModelAndView("redirect:/listing");

        return mav;
    }

}
