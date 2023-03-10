package com.uruchi.activitytracker.controller;

import com.uruchi.activitytracker.model.ToDoItemModel;
import com.uruchi.activitytracker.repositories.TodoItemRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.logging.Logger;

@Controller
public class ToDoFormController {
   // private final Logger logger = (Logger) LoggerFactory.getLogger(ToDoFormController.class);
    @Autowired
    private TodoItemRepository todoItemRepository;
    @GetMapping("/create-todo")
    public String showCreateForm(Model model) {
        ToDoItemModel todo = new ToDoItemModel();
        model.addAttribute("todoItem", todo);
        return "add-todo-item";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ToDoItemModel todoItemModel = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("todo", todoItemModel);
        return "update-todo-item";
    }
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        ToDoItemModel todoItemModel = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        todoItemRepository.delete(todoItemModel);
        return "redirect:/";
    }


}


