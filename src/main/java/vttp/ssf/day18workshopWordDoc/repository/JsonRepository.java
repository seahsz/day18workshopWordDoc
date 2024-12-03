package vttp.ssf.day18workshopWordDoc.repository;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp.ssf.day18workshopWordDoc.model.Todo;

import static vttp.ssf.day18workshopWordDoc.utils.HelperFunction.*;

@Repository
public class JsonRepository {

    @Autowired
    @Qualifier("redis-string")
    private RedisTemplate<String, String> template;

    // hset id "field" "value"
    public void saveTodos(JsonArray jsonArr) {

        HashOperations<String, String, String> hashOps = template.opsForHash();

        for (int i = 0; i < jsonArr.size(); i++) {

            JsonObject obj = jsonArr.getJsonObject(i);

            String id = obj.getString("id");

            hashOps.put(id, "name", obj.getString("name"));
            hashOps.put(id, "description", obj.getString("description"));
            hashOps.put(id, "due_date", String.valueOf(dateTOEpochMilliseconds(strToDate(obj.getString("due_date")))));
            hashOps.put(id, "priority_level", obj.getString("priority_level"));
            hashOps.put(id, "status", obj.getString("status"));
            hashOps.put(id, "created_at", String.valueOf(dateTOEpochMilliseconds(strToDate(obj.getString("created_at")))));
            hashOps.put(id, "updated_at", String.valueOf(dateTOEpochMilliseconds(strToDate(obj.getString("updated_at")))));
        }

    }

    // keys *
    public Set<String> getAllId() {

        return template.keys("*");

    }

    // hgetall id
    public Todo getInfoById(String id) {

        HashOperations<String, String, String> hashOps = template.opsForHash();

        Map<String, String> info = hashOps.entries(id);

        return new Todo(id, 
                             info.get("name"),
                             info.get("description"),
                             epochMillisecondsToDate(Long.parseLong(info.get("due_date"))),
                             info.get("priority_level"),
                             info.get("status"),
                             epochMillisecondsToDate(Long.parseLong(info.get("created_at"))),
                             epochMillisecondsToDate(Long.parseLong(info.get("updated_at"))));
    }

    // hset id field value
    public void addTodo(Todo todo) {

        HashOperations<String, String, String> hashOps = template.opsForHash();

        String id = todo.getId();

        hashOps.put(id, "name", todo.getName());
        hashOps.put(id, "description", todo.getDescription());
        hashOps.put(id, "due_date", String.valueOf(dateTOEpochMilliseconds(todo.getDueDate())));
        hashOps.put(id, "priority_level", todo.getPriority());
        hashOps.put(id, "status", todo.getStatus());
        hashOps.put(id, "created_at", String.valueOf(dateTOEpochMilliseconds(todo.getCreatedAt())));
        hashOps.put(id, "updated_at", String.valueOf(dateTOEpochMilliseconds(todo.getUpdatedAt())));

    }

    // del id
    public void deleteTodo(String id) {

        template.delete(id);
    }
    
}
