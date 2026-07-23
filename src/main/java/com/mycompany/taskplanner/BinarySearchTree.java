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

//organizes tasks by due date
public class BinarySearchTree {
    
    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    //store data and references to children
    class Node {
        Task task;
        Node left;
        Node right;
        
        public Node(Task task) {
            this.task = task;
            left = null;
            right = null;
        }
    }
    
    public void insert(Task task) {
        root = insertRecursive(root, task);
    }
    
    //if new task is earlier it goes left
    //if new task is later it goes right
    private Node insertRecursive(Node current, Task task) {
        if (current == null) {
            return new Node(task);
        }
        
        if (task.compareTo(current.task) < 0) {
            current.left = insertRecursive(current.left, task);
        } else {
            current.right = insertRecursive(current.right, task);
        }
        return current;
    }
    
    //in-order traversal
    public void printInOrder() {
        printInOrderRecursive(root);
    }
    
    private void printInOrderRecursive(Node node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.println(node.task);
            printInOrderRecursive(node.right);
        }
    }
    
    //prints all tasks on a specific date
    public void searchByDate(LocalDate date) {
        searchRecursive(root, date);
    }
    
    private void searchRecursive(Node node, LocalDate date) {
        if (node == null) {
            return;
        }
        
        searchRecursive(node.left, date);
        
        if (node.task.getDueDate().equals(date)) {
            System.out.println(node.task);
        }
        
        searchRecursive(node.right, date);
    }
    
    public void delete(Task task) {
        root = deleteRecursive(root, task);
    }

    private Node deleteRecursive(Node current, Task task) {
        if (current == null) {
            return null;
    }

    if (task.compareTo(current.task) < 0) {
        current.left = deleteRecursive(current.left, task);
    } else if (task.compareTo(current.task) > 0) {
        current.right = deleteRecursive(current.right, task);
    } else {

        
        if (current.left == null && current.right == null) {
            return null;
        }

        
        if (current.left == null) {
            return current.right;
        }
        if (current.right == null) {
            return current.left;
        }

      
        Node smallest = findMin(current.right);

        
        current.task = smallest.task;

       
        current.right = deleteRecursive(current.right, smallest.task);
    }

    return current;
}


    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
