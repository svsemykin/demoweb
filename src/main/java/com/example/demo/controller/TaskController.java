package com.example.demo.controller;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public Task create(@RequestBody Task task) {//создать запись
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable long id, @RequestBody Task task) {//редактирование записи по id
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable long id) {//удалить запись по id
        taskRepository.deleteById(id);
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable long id) {//получить запись по id
        return taskRepository.findById(id).orElse(null);
    }

    @GetMapping("/tasks")
    public Iterable<Task> get() {//получить все записи
        return taskRepository.findAll();
    }

    @PatchMapping("/tasks/{id}")//замена значения (галочка), способ 1, с кодом
    public void patchMethod(@PathVariable Long id, @RequestBody Task task){
        if (task.isDone())
            taskRepository.markAsDone(id);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")//замена значения (галочка), способ 2, без кода, галочка уже указана в @PatchMapping
    public void patchMethod(@PathVariable Long id){
            taskRepository.markAsDone(id);
    }

}

