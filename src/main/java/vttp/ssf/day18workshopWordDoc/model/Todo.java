package vttp.ssf.day18workshopWordDoc.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Task 3 - Create the following model with Spring Validation
public class Todo {

    @NotBlank(message = "Id cannot be empty")
    @Size(max = 50, message = "id cannot be longer than 50 characters")
    private String id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 10, max = 50, message = "Name must be within 10 and 50 characters (inclusive)")
    private String name;

    @Size(max = 255, message = "Description must be shorter than 255 characters")
    private String description;

    @FutureOrPresent(message = "Date must be future or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Due date must be selected")
    private Date dueDate;

    private String priority;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Created At date must be selected")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Updated At date must be selected")
    private Date updatedAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public Todo() {}
    public Todo(
            @NotBlank(message = "id cannot be empty") @Size(max = 50, message = "id cannot be longer than 50 characters") String id,
            @NotBlank(message = "name cannot be empty") @Size(min = 10, max = 50, message = "name must be within 10 and 50 characters (inclusive)") String name,
            @Size(max = 255, message = "description must be longer than 255 characters") String description,
            @FutureOrPresent(message = "Date must be future or present") Date dueDate, String priority, String status,
            Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
    
    
}
