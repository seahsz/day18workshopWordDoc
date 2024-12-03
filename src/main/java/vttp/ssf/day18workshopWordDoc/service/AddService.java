package vttp.ssf.day18workshopWordDoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.day18workshopWordDoc.model.Todo;
import vttp.ssf.day18workshopWordDoc.repository.JsonRepository;

@Service
public class AddService {

    @Autowired
    private JsonRepository jsonRepo;

    public void addTodoToRedis(Todo newTodo) {

        jsonRepo.addTodo(newTodo);
    }
    
}
