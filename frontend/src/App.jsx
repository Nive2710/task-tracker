import { useEffect, useState } from "react";
import { getAllTasks, createTask ,deleteTask ,updateTask} from "./api/TaskService";


function App() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState({
  title: "",
  description: "",
  status: "PENDING",
  priority: "HIGH",
  dueDate: "",
  projectId: 1
});

  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = async () => {
  try {
    const response = await getAllTasks();

    const data = response.data;

    setTasks(data?.content || data || []);
  } catch (error) {
    console.log("Error fetching tasks", error);
  }
};
const handleCreateTask = async () => {
  try {
    await createTask(newTask);

    setNewTask({
      title: "",
      description: "",
      status: "PENDING",
      priority: "HIGH",
      dueDate: "",
      projectId: 1
    });

    loadTasks();
  } catch (error) {
    console.log(error);
  }
};
const handleDeleteTask = async (id) => {
  try {
    await deleteTask(id);
    loadTasks();
  } catch (error) {
    console.log(error);
  }
};
const handleCompleteTask = async (task) => {
  try {
    const updatedTask = {
      ...task,
      status: "COMPLETED"
    };

    await updateTask(task.id, updatedTask);

    loadTasks();
  } catch (error) {
    console.log(error);
  }
};

  return (
    <div style={{ padding: "20px" }}>
      <h2>Task Tracker</h2>
      <div style={{ marginBottom: "20px" }}>
  <input
    type="text"
    placeholder="Title"
    value={newTask.title}
    onChange={(e) =>
      setNewTask({
        ...newTask,
        title: e.target.value
      })
    }
  />

  <input
    type="text"
    placeholder="Description"
    value={newTask.description}
    onChange={(e) =>
      setNewTask({
        ...newTask,
        description: e.target.value
      })
    }
  />

  <input
    type="date"
    value={newTask.dueDate}
    onChange={(e) =>
      setNewTask({
        ...newTask,
        dueDate: e.target.value
      })
    }
  />

  <button onClick={handleCreateTask}>
    Add Task
  </button>
</div>

      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Status</th>
            <th>Priority</th>
            <th>Due Date</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {tasks.length === 0 ? (
            <tr>
              <td colSpan="5">No tasks found</td>
            </tr>
          ) : (
            tasks.map((task) => (
              <tr key={task.id}>
                <td>{task.id}</td>
                <td>{task.title}</td>
                <td>{task.status}</td>
                <td>{task.priority}</td>
                <td>{task.dueDate}</td>
                <td>
  <button
    onClick={() => handleCompleteTask(task)}
  >
    Complete
  </button>

  <button
    onClick={() => handleDeleteTask(task.id)}
  >
    Delete
  </button>
</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default App;