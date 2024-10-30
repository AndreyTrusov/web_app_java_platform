package com.homework.uloha5.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import com.homework.uloha5.entity.Employee;
import com.homework.uloha5.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Value("${employee.jobTitles}")
    private String jobTitles;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployee(Model model) {

        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "employees/list";
    }

    @GetMapping("/view")
    public String viewEmployees(@RequestParam("employeeId") Long id, Model model) {

        Optional<Employee> employeeOpt = employeeService.findById(id);

        if (employeeOpt.isPresent()) {
            model.addAttribute("employee", employeeOpt.get());
            return "employees/view";
        } else {
            model.addAttribute("errorMessage", "Employee not found.");
            return "error";
        }
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") Long id, Model model) {

        employeeService.deleteById(id);
        return "redirect:/employees/list?deleted=true";

    }

    @GetMapping("/form/add")
    public String showFormForAdd(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("jobTitleList", getJobTitleList());
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

    private List<String> getJobTitleList() {
        return Arrays.asList(jobTitles.split(","));
    }
}
