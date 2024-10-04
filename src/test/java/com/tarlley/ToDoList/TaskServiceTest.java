package com.tarlley.ToDoList;

import com.tarlley.ToDoList.dto.task.TaskDTO;
import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;
import com.tarlley.ToDoList.exceptions.GlobalNotFoundException;
import com.tarlley.ToDoList.mapper.TaskMapper;
import com.tarlley.ToDoList.model.Category;
import com.tarlley.ToDoList.model.Task;
import com.tarlley.ToDoList.model.TaskList;
import com.tarlley.ToDoList.repository.TaskRepository;
import com.tarlley.ToDoList.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceTest {

    private Task task;

    @Mock
    private TaskRepository taskRepository;

    @Spy
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUpTask(){
        task = newTask(1, "Lavar Roupas", "Colocar roupas na maquina.", TaskStatus.STARTED,
                TaskPriority.AVERAGE, LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null);
    }

    @Test
    void shouldSaveTask(){
        task.setId(null);

        when(taskRepository.save(task)).thenReturn(task);
        TaskDTO taskDTO = taskMapper.toTaskDTO(task);

        assertEquals(taskDTO, taskService.saveNewTask(taskDTO));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFields")
    void shouldThrowErrorForInvalidFields(Task invalidTask, String expectedMessage) {
        TaskDTO invalidTaskDTO = taskMapper.toTaskDTO(invalidTask);
        RuntimeException exception = assertThrows(IllegalArgumentException.class, () -> taskService.saveNewTask(invalidTaskDTO));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void  ShouldReturnTask(){
        task.setId(1);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        TaskDTO taskDTO = taskMapper.toTaskDTO(task);
        assertEquals(taskService.findTaskById(1),taskDTO);

    }

    @Test
    void ShouldReturnTaskList(){

        Task task2 = newTask(2, "Lavar Roupas", "Colocar roupas na maquina.", TaskStatus.STARTED,
                TaskPriority.AVERAGE, LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task,task2));

        List<TaskDTO> taskDTO = taskMapper.toTaskDTO(Arrays.asList(task, task2));
        assertEquals(taskService.findAllTasks(),taskDTO);

    }

    @Test
    void WhenNullIDDoesNotUpdateTask(){
        task.setId(null);

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        TaskDTO taskDTO = taskMapper.toTaskDTO(task);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(taskDTO));

        assertEquals("Task ID Not Provided.",exception.getMessage());
    }

    @Test
    void ShouldThrowUserNotFoundException(){
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        GlobalNotFoundException exception = assertThrows(GlobalNotFoundException.class, () -> taskService.findTaskById(1));

        assertEquals("Task not found!",exception.getMessage());
    }
    private static Stream<Arguments> provideInvalidFields() {
        return Stream.of(
                Arguments.of(newTask(null, null, "Descrição válida", TaskStatus.STARTED, TaskPriority.AVERAGE,
                                LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null),
                        "Blank or null title."),

                Arguments.of(newTask(null, "Título válido", "Descrição válida", null, TaskPriority.AVERAGE,
                                LocalDateTime.now(), LocalDateTime.now().plusDays(5), new TaskList(), null),
                        "Invalid status field."),

                Arguments.of(newTask(null, "Título válido", "Descrição válida", TaskStatus.STARTED, TaskPriority.HIGH,
                                null, LocalDateTime.now().plusDays(5), null, List.of()),
                        "Invalid creation date.")
        );
    }

    private static Task newTask(Integer id, String title, String description, TaskStatus status, TaskPriority priority, LocalDateTime creationDate, LocalDateTime completionDate, TaskList taskList, List<Category> categories) {
        return new Task( id, title, description, status, priority, creationDate, completionDate, taskList, categories);
    }
}
