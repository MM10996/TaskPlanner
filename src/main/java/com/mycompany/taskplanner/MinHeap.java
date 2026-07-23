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

//stores tasks to get the next most urgent task quickly
public class MinHeap {
    private final ArrayList<Task> heap;
    
    public MinHeap() {
        heap = new ArrayList<>();
    }
    
    //helper methods
    public int size() {
        return heap.size();
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public Task peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }
    
    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    //add task to end and bubble up
    public void insert(Task task) {
        heap.add(task);
        
        int current = heap.size() - 1;
        
        while(current > 0) {
            int parent = (current - 1) / 2;
            
            if (heap.get(current).compareTo(heap.get(parent)) < 0) {
                swap(current, parent);
                current = parent;
            } else {
                break;
            }
        }
    }
    
    //save root, move last element to root, remove last, bubble down
    public Task remove() {
        if(heap.isEmpty()) {
            return null;
        }
        Task root = heap.get(0);
        
        Task last = heap.remove(heap.size() - 1);
        
        if(!heap.isEmpty()) {
            heap.set(0, last);
            bubbleDown(0);
        }
        
        return root;
    }
    
    //removes a specific task if still inside the heap
    public void remove(Task task) {
        heap.remove(task);
    }
    
    private void bubbleDown(int index) {
        int left = 2*index+1;
        int right = 2*index+2;
        int smallest = index;
        
        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }
        
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }
        
        if (smallest != index) {
            swap(index, smallest);
            bubbleDown(smallest);
        }
    }
    
}
