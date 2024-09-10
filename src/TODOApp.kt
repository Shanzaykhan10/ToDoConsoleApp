

class Task(
    val id: Int,
    var title: String,
    var description: String,
    var isCompleted: Boolean = false
)

fun main() {
    val tasks = mutableListOf<Task>()
    var count = 1
    println("--- Welcome to my To-Do App ---")

    while (true) {
        println()
        println("--Menu--")
        println("1. Add Task")
        println("2. View All Tasks")
        println("3. Update Task")
        println("4. Mark Task as Completed")
        println("5. Delete Task")
        println("6. Search task by title")
        println("7.  Sorted Task List")
        println("8. Exit")
        println()
        print("Choose an option: ")

        // Add task
        fun addTask(task: MutableList<Task>,nextId: Int) {
            println("--Enter task detail--")
            print("Task title: ")
            val title = readLine()!!
            print("Task description: ")
            val description = readLine()!!
            if (title.isNotEmpty() or description.isNotEmpty()) {
                val task = Task(count, title, description)
                tasks.add(task)
                count++
                println(" Task added.")
            } else println("task is empty")
        }

        // view All task
        fun viewAllTasks() {
            if (tasks.isEmpty()) {
                println("No tasks available.")
            } else {
                println("\nAll Task List")
                println()
                tasks.forEach { task ->
                    println(
                        "ID =  ${task.id}. \nTitle : ${task.title} .\nDescription : ${task.description}- " +
                                "\nStatus : ${if (task.isCompleted) "Completed" else "Not Completed"}"
                    )
                    println()
                }
            }
        }

        fun viewSortedTasks() {
            if (tasks.isEmpty()) {
                println("No tasks available.")
            } else {
                println("\nTask Sorted List")
                println()
                tasks.sortWith(compareBy { !it.isCompleted == false})
                tasks.forEach { task ->
                    println(
                        "ID =  ${task.id}. \nTitle : ${task.title} .\nDescription : ${task.description}- " +
                                "\nStatus : ${if (task.isCompleted) "Completed" else "Not Completed"}"
                    )
                    println()
                }
            }
        }

        // update task
        fun updateTask(tasks: MutableList<Task>) {
            print("Enter task ID to update: ")
            val id = readLine()?.toIntOrNull()
            val taskIndex = tasks.indexOfFirst { it.id == id }
            if (taskIndex != -1) {
                val task = tasks[taskIndex]
                print("Enter new title (leave blank to keep old title): ")
                val newTitle = readLine()!!
                if (newTitle.isNotEmpty()) {
                    task.title = newTitle
                }
                print("Enter new description (leave blank to keep old description): ")
                val newDescription = readLine()!!
                if (newDescription.isNotEmpty()) {
                    task.description = newDescription
                }
                println("Task updated.")
            } else {
                println("Task not found.")
            }
        }

        // cheeck status
        fun markTaskAsCompleted() {
            print("Enter task ID to mark as completed: ")
            val id = readLine()?.toIntOrNull()
            val task = tasks.find { it.id == id }
            if ( task != null) {
                task.isCompleted = true
                println("Task marked as completed.")
            } else {
                println("Task not found.")
            }
        }

        // search task
        fun searchTasksByTitle(tasks: MutableList<Task>) {
            print("Enter search term for task title: ")
            val searchTerm = readLine()!!

            val filteredTasks = tasks.filter { it.title.lowercase().contains(searchTerm) }
            if (filteredTasks.isEmpty()) {
                println("No tasks found '$searchTerm'.")
            } else {
                println("\nSearch Results")
                filteredTasks.forEach { task ->
                    println(
                        "ID =  ${task.id}. \nTitle : ${task.title} .\nDescription : ${task.description} " +
                                "\nStatus : ${if (task.isCompleted) "Completed" else "Not Completed"}"
                    )
                }
            }
        }

        // delete task
        fun deleteTask() {
            print("Enter task ID to delete: ")
            val id = readLine()?.toIntOrNull()
            if (tasks.removeIf { it.id == id }) {
                println("Task deleted.")
            } else {
                println("Task not found.")
            }
        }


        val choice = readLine()?.toIntOrNull()
        when (choice) {
            1 -> addTask(tasks, count)
            2 -> viewAllTasks()
            3 -> updateTask(tasks)
            4 -> markTaskAsCompleted()
            5 -> deleteTask()
            6 -> searchTasksByTitle(tasks)
            7 -> viewSortedTasks()
            8 -> {
                println("Exit..")
                break
            }

            else -> println("Invalid option, please try again.")
        }
    }
}