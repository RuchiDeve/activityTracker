package com.uruchi.activitytracker.config;

import com.uruchi.activitytracker.model.ToDoItemModel;
import com.uruchi.activitytracker.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ToDoItemDataLoader.class);

//    @Autowired
//    TodoItemRepository todoItemRepository;
    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (todoItemRepository.count() == 0) {
            ToDoItemModel todoItemModel1 = new ToDoItemModel("do my task");
            ToDoItemModel todoItemModel2 = new ToDoItemModel("standup meeting");

            todoItemRepository.save(todoItemModel1);
            todoItemRepository.save(todoItemModel2);
        }

        logger.info("Number of TodoItems: {}", todoItemRepository.count());
    }

}