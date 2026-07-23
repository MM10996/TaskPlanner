/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskplanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author molly
 */

//represents a task with title, due date, priority, category, and completed status
public class Task implements Comparable<Task> {
    
    private int id; //used by HashMap for lookup
    private String title; //task description
    private LocalDate dueDate; //used by BST to store by date
    private int priority; //used by heap
    private boolean completed; //track finished tasks   
    private String category; //store tasks by category
    
    public Task(int id, String title, LocalDate dueDate, int priority, String category){
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
        this.category = category;
    }
    
    //setters and getters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    //other methods
    @Override
    public int compareTo(Task other) {
        int dateComparison = this.dueDate.compareTo(other.dueDate);
        
        if (dateComparison != 0) {
            return dateComparison;
        }
        
       return Integer.compare(this.priority, other.priority);
    }
    
    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title
        + "\nDue: " + dueDate + "\nPriority: " + priority
        + "\nCategory: " + category + "\nCompleted: " + 
        (completed ? "Yes" : "No");
    }
    
}
