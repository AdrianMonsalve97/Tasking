package com.Amxx.Tasking.Load;

import java.util.Date;
import java.util.List;

import com.Amxx.Tasking.Service.TaskService;
import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Service.UsuarioService;
import org.springframework.data.domain.Sort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

@Configuration
class Load {

	@Bean
	CommandLineRunner initDatabase(UsuarioService userService) {
		return args -> {
			System.out.println("Probando la capa de servicios");

			Usuario user = new Usuario();
			user.setNombre("pepe");
			user.setNickname("Pepe2");
			user.setTelefono("12352336");
			user.setPassword("12345");
			user.getTask();
			userService.save(user);

			// Busca por id
			user = userService.findById(1L).get();

			// Actualiza datos del usuario
			user.setNombre("pepe");
			user.setNickname("Pepe1");
			user.setTelefono("12352336");
			userService.save(user);

			// Accediendo a la segunda pagina de User para un tamaño de pagina de 10,
			// ordenando por firstname de manera ascendente
			Page<Usuario> page = userService.getUsuario(0, 10, Sort.by(Direction.ASC, "nombre"));
			if (page.hasContent()) {
				System.out.println("Total de elementos:" + page.getTotalElements());
				System.out.println("Total de paginas:" + page.getTotalPages());
				imprimir("Usuarios de la pagina: " + page.getNumber(), page.getContent());
			}

		};
	}

	private void imprimir(String message, List<Usuario> users) {
		System.out.println(message);

		System.out.println("Usuarios encontrados: " + users.size());

		for (Usuario u : users) {
			System.out.println(u);
		}
	};

	@Bean
	CommandLineRunner initDatabaseTask(TaskService taskService) {
		return args -> {
			System.out.println("Probando la capa de servicios");

			Task task = new Task();
			task.setDescription("Tarea de tan");
			task.setFecha(new Date());
			task.setCantidad(0);
			task.getUsuario();
			taskService.save(task);

			// Busca por id
			task = taskService.findById(1L).get();

			// Actualiza datos del usuario
			// user.setNombre("pepe");
			// user.setNickname("Pepe43");
			// user.setTelefono("12352336");
			// userService.save(user);

			// Accediendo a la segunda pagina de User para un tamaño de pagina de 10,
			// ordenando por firstname de manera ascendente
			Page<Task> page = taskService.getTask(0, 10, Sort.by(Direction.ASC, "id"));
			if (page.hasContent()) {
				System.out.println("Total de elementos:" + page.getTotalElements());
				System.out.println("Total de paginas:" + page.getTotalPages());
				imprimirTask("Task de la pagina: " + page.getNumber(), page.getContent());
			}
		};
	}

	private void imprimirTask(String message, List<Task> tasks) {
		System.out.println(message);

		System.out.println("Usuarios encontrados: " + tasks.size());

		for (Task t : tasks) {
			System.out.println(t);
		}
	}

}
