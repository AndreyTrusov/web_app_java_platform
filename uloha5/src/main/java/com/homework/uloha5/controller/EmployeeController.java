package com.homework.uloha5.controller;

import org.springframework.ui.Model;
import com.homework.uloha5.entity.Employee;
import com.homework.uloha5.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployee(Model model) {

        List<Employee> employess = employeeService.findAll();

        model.addAttribute("employees", employess);

        return "employees/list";
    }

    @GetMapping("/list")
    public String viewEmployees(@RequestParam("employeeId") Long id, Model model) {

        Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);

        return "employees/view";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") Long id, Model model) {

        employeeService.deleteById(id);

        return "redirect:/employees/list";

    }

    @GetMapping("/form/add")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "employees/form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model) {

        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("employee") Employee employee){
        return "employee-confirmation";
    }
}
