package com.Amxx.Tasking.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.Amxx.Tasking.Dto.Mensaje;
import com.Amxx.Tasking.Dto.TaskDto;
import com.Amxx.Tasking.Models.Task;
import com.Amxx.Tasking.Security.Models.Usuario;
import com.Amxx.Tasking.Service.TaskService;
import com.Amxx.Tasking.Service.UsuarioService;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired

    UsuarioService usuarioService;

    @GetMapping("/LIst")
    public List<TaskDto> list() {
        List<Task> tasks = taskService.list();
        ModelMapper modelMapper = new ModelMapper();
        List<TaskDto> res = new ArrayList<>();
        for (Task task : tasks) {
            res.add(modelMapper.map(task, TaskDto.class));
            // res.add(mapper.map(cliente, ClienteDto.class));
        }

        return res;
    }

    @PostMapping("/addTask")
    private Task save(@RequestBody Task task) {
        taskService.save(task);
        return task;
    }

    @PostMapping(value = "/agregarTask/{id}")

    public ResponseEntity<?> create(@RequestBody TaskDto taskDto) {
        if (StringUtils.isBlank(taskDto.getDescription()))
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        else if (taskDto.getId() == null || taskDto.getId() < 0)
            return new ResponseEntity(new Mensaje("el id de la tarea debe ser mayor a 1"), HttpStatus.BAD_REQUEST);
        if (taskService.existsByDescription(taskDto.getDescription()))
            return new ResponseEntity(new Mensaje("Tarea ya existe"), HttpStatus.BAD_REQUEST);
        Task task = new Task(taskDto.getDescription(), taskDto.getId(), taskDto.getFecha(), taskDto.getUsuario(),
                taskDto.getCantidad());
        taskService.save(task);
        return new ResponseEntity(new Mensaje("tarea creada"), HttpStatus.OK);
    }

    // asignar tarea

    private ArrayList<Usuario> obtenerUsaurio(HttpServletRequest request) {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getSession()
                .getAttribute("usuarios");
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        return usuarios;
    }

    private void guardarUsuario(List<Usuario> usuario, HttpServletRequest request) {
        request.getSession().setAttribute("usuario", usuario);
    }

    @PostMapping(value = "/AsigagnarTarea/{id}")
    public String asignarTarea(@ModelAttribute TaskDto taskDto, HttpServletRequest request,
            RedirectAttributes redirectAttrs) {

        ArrayList<Usuario> usuarios = this.obtenerUsaurio(request);
        Usuario usuarioPorId = usuarioService.findFirstById(taskDto.getId());
        if (usuarioPorId == null) {
            redirectAttrs.addFlashAttribute("mensaje", "El usuario con el id " + taskDto.getId() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "Usuario no existe";
        }
        boolean encontrado = false;
        for (Usuario usuarioParaAsignarActual : usuarios) {
            if (usuarioParaAsignarActual.getId().equals(usuarioParaAsignarActual.getId())) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            usuarios.add(new Usuario(usuarioPorId.getNombre(), usuarioPorId.getId(),
                    usuarioPorId.getNickname(), usuarioPorId.getRoles(), usuarioPorId.getTask(),
                    usuarioPorId.getPassword(), usuarioPorId.getTelefono()));
        }

        this.guardarUsuario(usuarios, request);
        return "usuario agregado " + usuarioPorId;

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!taskService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        taskService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

    // consulta de criteria //

    @GetMapping("/consultacriteria")
    public List<Task> findAllTasks(@RequestParam(required = false) Long id,
            @RequestParam(required = false) Long cantidad) {

        return taskService.findTaskByIdAndCantidad(id, cantidad);

    }

}
