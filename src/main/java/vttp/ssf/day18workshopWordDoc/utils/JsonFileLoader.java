package vttp.ssf.day18workshopWordDoc.utils;

import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import vttp.ssf.day18workshopWordDoc.repository.JsonRepository;

@Component
public class JsonFileLoader implements CommandLineRunner {

    private static final String JSON_TODO_PATH = "static/todos.txt";

    Logger logger = Logger.getLogger(JSON_TODO_PATH.getClass().getName());

    @Autowired
    private JsonRepository jsonRepo;

    // Task 2 - Load data found in todos.txt as a Map into Redis
    @Override
    public void run(String... args) {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(JSON_TODO_PATH);
            JsonReader reader = Json.createReader(is);
            JsonArray jsonArr = reader.readArray();

            jsonRepo.saveTodos(jsonArr);

        } catch (Exception e) {
            logger.warning("Error obtaining List of City from Json File");
            e.printStackTrace();
        }

    }
    
}
