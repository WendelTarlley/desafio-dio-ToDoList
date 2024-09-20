package com.tarlley.ToDoList;

import com.tarlley.ToDoList.enumeretad.TaskPriority;
import com.tarlley.ToDoList.enumeretad.TaskStatus;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private TaskMapper taskMapper;

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

        assertEquals(taskDTO, taskService.saveTask(task));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFields")
    void shouldThrowErrorForInvalidFields(Task invalidTask, String expectedMessage) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.saveTask(invalidTask));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void  ShouldReturnTask(){
        task.setId(1);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        TaskDTO taskDTO = taskMapper.toTaskDTO(task);
        assertEquals(taskService.findTaskById(1),TaskDTO);

    }

    @Test
    void ShouldReturnTaskList(){

        Task task2 = newTask(2, "Lavar Roupas", "Colocar roupas na maquina.", TaskStatus.STARTED,
                TaskPriority.AVERAGE, LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task,task2));

        assertEquals(taskService.findAllTasks(),Arrays.asList(task,task2));

    }

    private static Stream<Arguments> provideInvalidFields() {
        return Stream.of(
                Arguments.of(newTask(null, null, "Descrição válida", TaskStatus.STARTED, TaskPriority.AVERAGE,
                                LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null),
                        "Invalid data, review task fields."),

                Arguments.of(newTask(null, "Título válido", "Descrição válida", null, TaskPriority.AVERAGE,
                                LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null),
                        "Invalid data, review task fields."),

                Arguments.of(newTask(null, "Título válido", null, TaskStatus.STARTED, TaskPriority.AVERAGE,
                                LocalDateTime.now(), LocalDateTime.now().plusDays(5), null, null),
                        "Invalid data, review task fields.")
        );
    }

    private static Task newTask(Integer id, String title, String description, TaskStatus status, TaskPriority priority, LocalDateTime creationDate, LocalDateTime completionDate, TaskList taskList, List<Category> categories) {
        return new Task( id, title, description, status, priority, creationDate, completionDate, taskList, categories);
    }
}
