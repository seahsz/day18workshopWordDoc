package vttp.ssf.day18workshopWordDoc.controller;

import static vttp.ssf.day18workshopWordDoc.utils.Constants.ATTR_ID;
import static vttp.ssf.day18workshopWordDoc.utils.Constants.ATTR_TODO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.day18workshopWordDoc.service.ListingService;

@Controller
@RequestMapping
public class ToDoController {

    @Autowired
    private ListingService listingSvc;

    @GetMapping("/deleteTodo/{orderId}")
    public ModelAndView getDeleteTodo(@PathVariable String orderId) {

        listingSvc.deleteTOdo(orderId);

        ModelAndView mav = new ModelAndView("redirect:/listing");

        return mav;
    }

    @GetMapping("/updateTodo/{orderId}")
    public ModelAndView getUpdateTodo(@PathVariable String orderId) {

        ModelAndView mav = new ModelAndView("add");
        mav.addObject(ATTR_TODO, listingSvc.getTodoById(orderId));
        mav.addObject(ATTR_ID, orderId);

        return mav;
    }
    
}
