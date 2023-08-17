package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
    
    // ArrayList to store todos
    private static List<Todo> todos = new ArrayList<>();

    // count for id for each todos
    private static int todosCount = 0;

    // HardCoded todos
    static{
        todos.add(new Todo(++todosCount, "in28minutes", "Get AWS Certified 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "in28minutes", "Learn DevOps 1", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "in28minutes", "Learn Full Stack Development 1", LocalDate.now().plusYears(3), false));
    }

    // Method to findByUsername with String username input parameter
    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = 
            todo -> todo.getUsername().equalsIgnoreCase(username); 
        return todos.stream().filter(predicate).toList();
    }

    // Method that add new todo in the list of todos
    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    // Method to delete a todo
    public void deleteById(int id){
        // todo.getId() == id

        // Lambdas function, check each todo against the input parameter
        // todo -> todo.getId() == id
        Predicate<? super Todo> predicate = todo -> todo.getId() == id; 
        todos.removeIf(predicate);
    }

    // filtering from the list of todos and return the todo which is equal to id
    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id; 
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}