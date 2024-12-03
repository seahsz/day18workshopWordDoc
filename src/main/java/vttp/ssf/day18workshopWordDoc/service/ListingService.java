package vttp.ssf.day18workshopWordDoc.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.day18workshopWordDoc.model.Todo;
import vttp.ssf.day18workshopWordDoc.repository.JsonRepository;

@Service
public class ListingService {

    @Autowired
    private JsonRepository jsonRepo;

    public List<Todo> getTodos() {

        Set<String> ids = jsonRepo.getAllId();

        return ids.stream()
                .map(id -> { return jsonRepo.getInfoById(id); })
                .collect(Collectors.toList());
    }

    public void deleteTOdo(String id) {

        jsonRepo.deleteTodo(id);
    }

    public Todo getTodoById(String id) {

        return jsonRepo.getInfoById(id);
    }

}
