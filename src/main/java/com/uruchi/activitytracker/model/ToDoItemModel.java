package com.uruchi.activitytracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "todo_item")
@Getter
@Setter
//@NoArgsConstructor
public class ToDoItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String Title;
    @NotBlank(message = "Description is required")
    private String Description;

    private boolean Status;

    private Instant CreatedAt;

@Getter
@Setter
    private Instant UpDatedAt;


    private Instant CompletedAt;
    public  String complete;

    public ToDoItemModel(){}
   public ToDoItemModel(String Description) {
       this.Description = Description;
       this.CompletedAt = Instant.now();
       this.UpDatedAt = Instant.now();
       this.CreatedAt = Instant.now();
       this.Status = false;

   }

//    @Override
//    public String toString() {
//        return "TodoItemModel{" +
//                "id=" + id +
//                ", Tittle='" + Tittle + '\'' +
//                ", Description='" + Description + '\'' +
//                ", Status=" + Status +
//                ", CreatedAt=" + CreatedAt +
//                ", UpDatedAt=" + UpDatedAt +
//                ", CompletedAt=" + CompletedAt +
//                '}';
@Override
public String toString() {
    return String.format("TodoItem{id=%d, Description='%s', CompletedAt='%s', Complete='%s', CreatedAt='%s', UpDatedAt='%s'}",
            id, Description, Status, CreatedAt, UpDatedAt, CreatedAt);
    }
}
