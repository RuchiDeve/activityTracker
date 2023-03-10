package com.uruchi.activitytracker.repositories;

import com.uruchi.activitytracker.model.ToDoItemModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TodoItemRepository extends JpaRepository<ToDoItemModel,Long> {
    @Modifying
    @Transactional
    @Query(value = "update ",nativeQuery = true)
    void editTodo(String description);

}
