package com.mycompany.taskplanner;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskPlanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Task Planner Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Next Task");
            System.out.println("4. Complete Task");
            System.out.println("5. View Completed Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number between 1 and 6.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter task ID (integer): ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (manager.getTaskById(id) != null) {
                            System.out.println("ID already exists, choose a unique ID.");
                            break;
                        }

                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter due date (YYYY-MM-DD): ");
                        LocalDate date = LocalDate.parse(scanner.nextLine());

                        System.out.print("Enter priority (positive integer, lower = more urgent): ");
                        int priority = scanner.nextInt();
                        scanner.nextLine(); 
                        if (priority <= 0) {
                            System.out.println("Priority must be a positive integer.");
                            break;
                        }

                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();

                        Task newTask = new Task(id, title, date, priority, category);
                        manager.addTask(newTask);
                        System.out.println("Task added successfully.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format, please use YYYY-MM-DD.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input type, make sure to enter integers where required.");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.println("\nAll Tasks:");
                    manager.showAllTasks();
                    break;

                case 3:
                    Task next = manager.getNextTask();
                    System.out.println("\nNext Task:");
                    System.out.println(next != null ? next : "No tasks available.");
                    break;

                case 4:
                    try {
                        System.out.print("Enter ID of task to complete: ");
                        int completeId = scanner.nextInt();
                        scanner.nextLine();
                        manager.completeTask(completeId);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID, must be an integer.");
                        scanner.nextLine();
                    }
                    break;

                case 5:
                    System.out.println("\nCompleted Tasks:");
                    manager.showCompletedTasks();
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting Task Planner.");
                    break;

                default:
                    System.out.println("Invalid choice, enter a number between 1 and 6.");
            }
        }

        scanner.close();
    }
}