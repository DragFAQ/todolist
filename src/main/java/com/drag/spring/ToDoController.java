package com.drag.spring;

import com.drag.spring.model.ToDo;
import com.drag.spring.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoController {
    private ToDoService toDoService;

    @Autowired(required = true)
    @Qualifier(value = "toDoService")
    public void setToDoService(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("todolist");
        modelAndView.addObject("todolist", toDoService.listToDosByStatus(-1));

        return modelAndView;
    }

/*    @RequestMapping(value = "/todolist", method = RequestMethod.GET)
    public String listToDos(Model model) {
        model.addAttribute("todo", new ToDo());
        model.addAttribute("listToDos", this.toDoService.listToDosByStatus(-1));
        return "todo";
    }
*/
    @RequestMapping(value = "/todo/add", method = RequestMethod.POST)
    public String addToDo(@ModelAttribute("todo") ToDo toDo) {
        if (toDo.getId() == 0) {
            this.toDoService.addToDo(toDo);
        } else {
            this.toDoService.updateToDo(toDo);
        }

        return "redirect:/todolist";
    }

    @RequestMapping("/remove/{id}")
    public String removeToDo(@PathVariable("id") int id) {
        this.toDoService.removeToDo(id);

        return "redirect:/todolist";
    }

    @RequestMapping("/edit/{id}")
    public String editToDo(@PathVariable("id") int id, Model model) {
        model.addAttribute("todo", this.toDoService.getToDoById(id));
        model.addAttribute("listToDos", this.toDoService.listToDosByStatus(-1));

        return "todo";
    }

    @RequestMapping("/mark-done/{id}")
    public String setDone(@PathVariable("id") int id) {
        this.toDoService.setDone(id);

        return "redirect:/todolist";
    }
}
