data class Task(
    var title: String,
    var isComplete: Boolean = false
)

// Adds a new task to the task list.
fun addTask(tasks: MutableList<Task>) {
    print("Enter task title: ")
    val title = readln().trim()

    if (title.isNotEmpty()) {
        tasks.add(Task(title))
        println("Task added.")
    } else {
        println("Task title cannot be empty.")
    }
}

// Displays all tasks in the task list.
fun viewTasks(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks found.")
        return
    }

    println("\nTask List:")
    for ((index, task) in tasks.withIndex()) {
        val status = if (task.isComplete) "[Complete]" else "[Pending]"
        println("${index + 1}. ${task.title} $status")
    }
}

// Displays only completed tasks.
fun viewCompletedTasks(tasks: MutableList<Task>) {
    val completedTasks = tasks.filter { it.isComplete }

    if (completedTasks.isEmpty()) {
        println("No completed tasks found.")
        return
    }

    println("\nCompleted Tasks:")
    for ((index, task) in completedTasks.withIndex()) {
        println("${index + 1}. ${task.title} [Complete]")
    }
}

// Displays only pending tasks.
fun viewPendingTasks(tasks: MutableList<Task>) {
    val pendingTasks = tasks.filter { !it.isComplete }

    if (pendingTasks.isEmpty()) {
        println("No pending tasks found.")
        return
    }

    println("\nPending Tasks:")
    for ((index, task) in pendingTasks.withIndex()) {
        println("${index + 1}. ${task.title} [Pending]")
    }
}

// Marks a selected task as complete.
fun completeTask(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks to complete.")
        return
    }

    viewTasks(tasks)
    print("Enter the number of the task to mark complete: ")
    val input = readln().toIntOrNull()

    if (input == null || input !in 1..tasks.size) {
        println("Invalid task number.")
        return
    }

    tasks[input - 1].isComplete = true
    println("Task marked complete.")
}

// Edits the title of an existing task.
fun editTask(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks to edit.")
        return
    }

    viewTasks(tasks)
    print("Enter the number of the task to edit: ")
    val input = readln().toIntOrNull()

    if (input == null || input !in 1..tasks.size) {
        println("Invalid task number.")
        return
    }

    print("Enter the new task title: ")
    val newTitle = readln().trim()

    if (newTitle.isNotEmpty()) {
        tasks[input - 1].title = newTitle
        println("Task updated.")
    } else {
        println("Task title cannot be empty.")
    }
}

// Removes a selected task from the list.
fun removeTask(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks to remove.")
        return
    }

    viewTasks(tasks)
    print("Enter the number of the task to remove: ")
    val input = readln().toIntOrNull()

    if (input == null || input !in 1..tasks.size) {
        println("Invalid task number.")
        return
    }

    tasks.removeAt(input - 1)
    println("Task removed.")
}

// Displays the main menu options.
fun showMenu() {
    println("\nTask Manager")
    println("1. Add Task")
    println("2. View All Tasks")
    println("3. View Completed Tasks")
    println("4. View Pending Tasks")
    println("5. Complete Task")
    println("6. Edit Task")
    println("7. Remove Task")
    println("8. Exit")
    print("Choose an option: ")
}

// Runs the main task manager program loop.
fun main() {
    val tasks = mutableListOf<Task>()
    var running = true

    while (running) {
        showMenu()
        val choice = readln().toIntOrNull()

        when (choice) {
            1 -> addTask(tasks)
            2 -> viewTasks(tasks)
            3 -> viewCompletedTasks(tasks)
            4 -> viewPendingTasks(tasks)
            5 -> completeTask(tasks)
            6 -> editTask(tasks)
            7 -> removeTask(tasks)
            8 -> {
                println("Goodbye.")
                running = false
            }
            else -> println("Invalid option. Try again.")
        }
    }
}