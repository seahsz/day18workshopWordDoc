package vttp.ssf.day18workshopWordDoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vttp.ssf.day18workshopWordDoc.model.Todo;
import vttp.ssf.day18workshopWordDoc.service.AddService;

import static vttp.ssf.day18workshopWordDoc.utils.Constants.*;

import java.util.UUID;

@Controller
@RequestMapping("/add")
public class AddController {

    @Autowired
    private AddService addSvc;

    @GetMapping
    public ModelAndView getAdd() {

        ModelAndView mav = new ModelAndView("add");

        // Create new Todo object with ID pre-populated by the server
        Todo newTodo = new Todo();

        mav.addObject(ATTR_ID, UUID.randomUUID().toString());
        mav.addObject(ATTR_TODO, newTodo);

        return mav;
    }

    @PostMapping
    public ModelAndView postAdd(@Valid Todo todo, BindingResult binding,
            @RequestBody MultiValueMap<String, String> form) {

        ModelAndView mav = new ModelAndView("add");

        if (binding.hasErrors()) {
            mav.addObject(ATTR_ID, form.getFirst("id"));
            return mav;
        }

        // Set ID to Todo object
        todo.setId(form.getFirst("id"));

        // Call Service to add Todo to Redis
        addSvc.addTodoToRedis(todo);

        // Create new Todo object with ID pre-populated by the server [to prepare to accept next input - similar to getAdd()]
        Todo newTodo = new Todo();

        mav.addObject(ATTR_ID, UUID.randomUUID().toString());
        mav.addObject(ATTR_TODO, newTodo);

        return mav;
    }

}
