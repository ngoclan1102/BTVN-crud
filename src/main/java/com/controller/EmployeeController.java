package com.controller;

import com.model.Category;
import com.model.Employee;
import com.service.CategoryService;
import com.service.EmployeeService;
import com.service.ICategoryService;
import com.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {
@Autowired
    IEmployeeService employeeService;
@Autowired
ICategoryService categoryService;


@GetMapping("/employee")
    public ModelAndView show() {
    ModelAndView modelAndView = new ModelAndView("show");
    List<Employee> employeeList = employeeService.getAll();
    modelAndView.addObject("employees",employeeList);
    return modelAndView;
}

// create
@GetMapping("/create")
    public ModelAndView create(){
    ModelAndView modelAndView = new ModelAndView("create");
    modelAndView.addObject("employee", new Employee());
    modelAndView.addObject("category", categoryService.getAll());
    return modelAndView;
}

@PostMapping("/create")
    public String createEmployee(@ModelAttribute(value = "employee") Employee employee,@RequestParam long idCategory,@RequestParam MultipartFile upImg){
    Category category = new Category();
    category.setId(idCategory);
    employee.setCategory(category);
    String nameImg = upImg.getOriginalFilename();
    try {
        FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\Computer\\Documents\\Module4\\Bài4\\TH1\\src\\main\\webapp\\WEB-INF\\file\\" + nameImg));
    employee.setImg("/file/" + nameImg);
    employeeService.add(employee);
} catch (IOException e) {
        System.err.println("chưa upload ảnh");
    }

    return "redirect:/employee";
}

// delete
    @GetMapping("/delete")
    public String delete(long id) {
    employeeService.delete(id);
        return "redirect:/employee";
}

// edit
@GetMapping("/edit")
public ModelAndView edit(@RequestParam long id){
    ModelAndView modelAndView = new ModelAndView("edit");
    modelAndView.addObject("employees", employeeService.findId(id));
    modelAndView.addObject("category", categoryService.getAll());
    return modelAndView;
}

    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute(value = "employees") Employee employee,@RequestParam long idCategory,@RequestParam MultipartFile upImg){
        Category category = new Category();
        category.setId(idCategory);
        employee.setCategory(category);
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\Computer\\Documents\\Module4\\Bài4\\TH1\\src\\main\\webapp\\WEB-INF\\file\\" + upImg.getOriginalFilename()));
            employee.setImg("/file/" + upImg.getOriginalFilename());
            employeeService.add(employee);
        } catch (IOException e) {
            System.err.println("chưa upload ảnh");
        }

        return "redirect:/employee";
    }
    }
