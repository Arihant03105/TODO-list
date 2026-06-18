import java.util.ArrayList;
import java.util.Scanner;

// Task class - ek individual task ko represent karta hai
class Task {
    private String name;        // Task ka naam
    private boolean isComplete; // Task complete hua ya nahi

    // Constructor
    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    // Getters aur Setters
    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    // Task ko display karne ke liye
    public String getDisplay() {
        String status = isComplete ? "[✓]" : "[ ]";
        return status + " " + name;
    }
}

// Main TaskTracker class
public class TaskTracker {
    private ArrayList<Task> tasks;  // Saare tasks ko store karne ke liye ArrayList
    private Scanner scanner;        // User input lene ke liye

    // Constructor
    public TaskTracker() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // ========== CRUD OPERATIONS ==========

    // CREATE - Naya task add karna
    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        tasks.add(newTask);
        System.out.println("\n✓ Task added successfully: \"" + taskName + "\"");
        System.out.println("Total tasks: " + tasks.size());
    }

    // READ - Saare tasks ko display karna
    public void viewTasks() {
        System.out.println("\n========== YOUR TASKS ==========");
        
        if (tasks.isEmpty()) {
            System.out.println("(No tasks found. Add one with option 1!)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).getDisplay());
            }
        }
        System.out.println("================================\n");
    }

    // UPDATE - Task ko complete mark karna
    public void markTaskComplete() {
        if (tasks.isEmpty()) {
            System.out.println("\n(No tasks to mark complete)\n");
            return;
        }

        viewTasks();
        System.out.print("Enter task number to mark as complete: ");
        
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine(); // Buffer clear karna zaroori hai

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("✗ Invalid task number!\n");
                return;
            }

            Task selectedTask = tasks.get(taskNumber - 1);
            
            if (selectedTask.isComplete()) {
                System.out.println("✓ Task is already marked as complete!\n");
            } else {
                selectedTask.markComplete();
                System.out.println("\n✓ Marked as complete: \"" + selectedTask.getName() + "\"\n");
            }
        } catch (Exception e) {
            System.out.println("✗ Invalid input!\n");
            scanner.nextLine(); // Error handling
        }
    }

    // DELETE - Task ko remove karna
    public void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("\n(No tasks to delete)\n");
            return;
        }

        viewTasks();
        System.out.print("Enter task number to delete: ");
        
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine(); // Buffer clear karna

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("✗ Invalid task number!\n");
                return;
            }

            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("\n✗ Deleted: \"" + removedTask.getName() + "\"");
            System.out.println("Tasks remaining: " + tasks.size() + "\n");
        } catch (Exception e) {
            System.out.println("✗ Invalid input!\n");
            scanner.nextLine();
        }
    }

    // ========== MENU SYSTEM ==========

    // Menu ko display karna
    public void displayMenu() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║  CLI TASK TRACKER v1.0       ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();
        System.out.println("  1. Add Task       (CREATE)");
        System.out.println("  2. View Tasks     (READ)");
        System.out.println("  3. Mark Complete  (UPDATE)");
        System.out.println("  4. Delete Task    (DELETE)");
        System.out.println("  5. Exit");
        System.out.println();
        System.out.print("Choose an option (1-5): ");
    }

    // Menu ko handle karna aur appropriate action perform karna
    public void run() {
        boolean isRunning = true;

        System.out.println("\n=== Starting CLI Task Tracker ===\n");

        while (isRunning) {
            displayMenu();

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Buffer clear karna

                // Switch-Case menu logic
                switch (choice) {
                    case 1:
                        // CREATE
                        System.out.println("\n========== ADD TASK ==========");
                        System.out.print("Enter task name: ");
                        String taskName = scanner.nextLine();
                        
                        if (taskName.trim().isEmpty()) {
                            System.out.println("✗ Task name cannot be empty!\n");
                        } else {
                            addTask(taskName);
                        }
                        break;

                    case 2:
                        // READ
                        viewTasks();
                        break;

                    case 3:
                        // UPDATE
                        System.out.println("\n========== MARK COMPLETE ==========");
                        markTaskComplete();
                        break;

                    case 4:
                        // DELETE
                        System.out.println("\n========== DELETE TASK ==========");
                        deleteTask();
                        break;

                    case 5:
                        // EXIT
                        System.out.println("\n✓ Goodbye! Exiting task tracker...\n");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("✗ Invalid option! Please enter a number between 1-5.\n");
                }

            } catch (Exception e) {
                System.out.println("✗ Invalid input! Please enter a valid number.\n");
                scanner.nextLine(); // Error handling
            }
        }

        scanner.close();
    }

    // ========== MAIN METHOD ==========
    public static void main(String[] args) {
        TaskTracker tracker = new TaskTracker();
        tracker.run();
    }
}