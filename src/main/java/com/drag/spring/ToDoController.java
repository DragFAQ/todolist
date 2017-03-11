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


    @RequestMapping(value = "/todolist/{status}", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable int status) {
        ModelAndView modelAndView = new ModelAndView("todolist");
        modelAndView.addObject("todo", new ToDo());
        modelAndView.addObject("status", status);
        modelAndView.addObject("todolist", toDoService.listToDosByStatus(status));

        return modelAndView;
    }

    @RequestMapping(value = "/todo/add/{status}", method = RequestMethod.POST)
    public String addToDo(@ModelAttribute("todo") ToDo toDo, @PathVariable int status) {
        if (toDo.getId() == 0) {
            this.toDoService.addToDo(toDo);
        } else {
            this.toDoService.updateToDo(toDo);
        }

        return "redirect:/todolist/" + status;
    }

    @RequestMapping("/remove/{status}/{id}")
    public String removeToDo(@PathVariable int status, @PathVariable("id") int id) {
        this.toDoService.removeToDo(id);

        return "redirect:/todolist/" + status;
    }

    @RequestMapping("/edit/{status}/{id}")
    public ModelAndView editToDo(@PathVariable int status, @PathVariable("id") int id, Model model) {
        ModelAndView modelAndView = new ModelAndView("todolist");
        modelAndView.addObject("todo", this.toDoService.getToDoById(id));
        modelAndView.addObject("status", status);
        modelAndView.addObject("todolist", this.toDoService.listToDosByStatus(status));

        return modelAndView;
    }

    @RequestMapping("/mark-done/{status}/{id}")
    public String setDone(@PathVariable int status, @PathVariable("id") int id) {
        this.toDoService.setDone(id);

        return "redirect:/todolist/" + status;
    }
}
