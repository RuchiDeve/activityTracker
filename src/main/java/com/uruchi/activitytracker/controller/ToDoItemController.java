package com.uruchi.activitytracker.controller;

import com.uruchi.activitytracker.model.ToDoItemModel;
import com.uruchi.activitytracker.repositories.TodoItemRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;

@Controller
public class ToDoItemController {
    private final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);
    @Autowired
    private TodoItemRepository todoItemRepository;
@GetMapping("/")
    public ModelAndView index(){
   // logger.debug(msg :"request to GET index");
    //logger.debug("reguest to GET index");
    logger.debug("request to Get index");
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("todoItems", todoItemRepository.findAll());
    modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
    return modelAndView;
}

    @PostMapping("/todo")
    public String createTodoItem(ToDoItemModel toDoItemModel, BindingResult result, Model model) {
    if (result.hasErrors()) {
            return "add-todo-item";
        }

        toDoItemModel.setCreatedAt(Instant.now());
        toDoItemModel.setUpDatedAt(Instant.now());
        toDoItemModel.setCompletedAt(Instant.now());
        todoItemRepository.save(toDoItemModel);
        return "redirect:/";
    }

//    @PostMapping("/todo/edit")
//    public String updateTodoItem(ToDoItemModel toDoItemModel, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "update-todo-item";
//        }
//
//        toDoItemModel.setCreatedAt(Instant.now());
//        toDoItemModel.setUpDatedAt(Instant.now());
//        toDoItemModel.setCompletedAt(Instant.now());
//        todoItemRepository.editTodo (toDoItemModel.getDescription());
//        return "redirect:/";
    //}
        @PostMapping("/todo/{id}")
        public String updateTodoItem ( @PathVariable("id") long id, @Valid ToDoItemModel toDoItemModel, BindingResult
        result, Model model){
            if (result.hasErrors()) {
               toDoItemModel.setId(id);
                return "update-todo-item";
            }

            toDoItemModel.setUpDatedAt(Instant.now());
            todoItemRepository.save(toDoItemModel);
            return "redirect:/";
        }
    }
