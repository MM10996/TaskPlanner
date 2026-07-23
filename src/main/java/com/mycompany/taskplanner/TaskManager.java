/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskplanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author molly
 */

//connects all the data structures together
public class TaskManager {
    
    private final MinHeap heap;
    private final BinarySearchTree tree;
    private final HashMap<Integer, Task> taskMap;
    private final ArrayList<Task> completedTasks;
    
    public TaskManager() {
        heap = new MinHeap();
        tree = new BinarySearchTree();
        taskMap = new HashMap<>();
        completedTasks = new ArrayList<>();
    }
    
    //adds task to heap, BST, and HashMap
    public void addTask(Task task) {
        heap.insert(task);
        tree.insert(task);
        taskMap.put(task.getId(), task);
    }
    
    public Task getNextTask() {
      while (!heap.isEmpty()) {
        Task top = heap.peek();
        
        if (!top.getCompleted()) {
            return top;
        }
        heap.remove();
    }
      return null;
    }
    
    //uses HashMap
    public void completeTask(int id) {
        Task task = taskMap.get(id);
        
        if (task != null) {
            task.setCompleted(true);
            completedTasks.add(task);
            taskMap.remove(task.getId());
            tree.delete(task);
            System.out.println("Task successfully completed.");
        } else {
            System.out.println("Task not found.");
        }
    }
    
    public void showAllTasks() {
        tree.printInOrder();
    }
    
    public void showCompletedTasks() {
        for (Task task : completedTasks) {
            System.out.println(task);
        }
    }
    
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }
}
