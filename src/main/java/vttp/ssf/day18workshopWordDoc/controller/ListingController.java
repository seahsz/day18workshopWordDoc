package vttp.ssf.day18workshopWordDoc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import vttp.ssf.day18workshopWordDoc.model.Todo;
import vttp.ssf.day18workshopWordDoc.service.ListingService;

import static vttp.ssf.day18workshopWordDoc.utils.Constants.*;

@Controller
@RequestMapping
public class ListingController {

    @Autowired
    private ListingService listingSvc;

    @GetMapping("/listing")
    public ModelAndView getListing(HttpSession session) {

        ModelAndView mav = new ModelAndView();

        // Task 4 - Check if Http session is present - if not redirect to custom error page ("refused.html")
        String fullName = (String) session.getAttribute("fullName");

        if (fullName == null) {
            mav.setViewName("refused");
            return mav;
        }

        List<Todo> todos = listingSvc.getTodos();

        mav.setViewName("listing");
        mav.addObject(ATTR_TODO_LIST, todos);

        return mav;
    }

    @GetMapping("/listing/filter")
    public ModelAndView getListingFilter(@RequestParam String statusOpt) {

        List<Todo> todos = listingSvc.getTodos();

        todos = todos.stream()
                .filter(todo -> todo.getStatus().equals(statusOpt))
                .collect(Collectors.toList());

        ModelAndView mav = new ModelAndView("listing");
        mav.addObject(ATTR_TODO_LIST, todos);

        return mav;
    }

}
