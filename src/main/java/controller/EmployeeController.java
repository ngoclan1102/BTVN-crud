package controller;

import model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.EmployeeService;

import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.List;

@Controller
public class EmployeeController {


@GetMapping("/employee")
    public ModelAndView show() {
    ModelAndView modelAndView = new ModelAndView("show");
    List<Employee> employeeList = EmployeeService.findAll();
    modelAndView.addObject("employee",employeeList);
    return modelAndView;
}

// create
@GetMapping("/create")
    public String create(){
    return "create";
}

@PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee, @RequestParam MultipartFile upImg){
    String img = upImg.getOriginalFilename();
    try {
        FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\Computer\\Documents\\Module4\\Bài4\\TH1\\src\\main\\webapp\\WEB-INF\\file\\" + img));
    String urlImg ="/i/file/" + img;
    employee.setImg(urlImg);
} catch (IOException e) {
        System.err.println("chưa upload ảnh");
    }
    EmployeeService.add(employee);
    return "redirect:/employee";
}

// delete
    @GetMapping("/delete")
    public String delete(int id) {
    EmployeeService.delete(id);
        return "redirect:/employee";
}

// edit
    @GetMapping("/edit")
    public ModelAndView edit(int id){
    ModelAndView modelAndView = new ModelAndView("edit");
    Employee  employee = EmployeeService.findEmployee(id);
    modelAndView.addObject("employee",employee);
    return modelAndView;
    }

    @PostMapping("/edit")
    public String createEmployee(@ModelAttribute Employee employee,@RequestParam int id, @RequestParam MultipartFile upImg){
        String img = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("C:\\Users\\Computer\\Documents\\Module4\\Bài4\\TH1\\src\\main\\webapp\\WEB-INF\\file\\" + img));
            String urlImg ="/i/file/" + img;
            employee.setImg(urlImg);
        } catch (IOException e) {
            System.err.println("chưa upload ảnh");
        }
        EmployeeService.edit(id,employee);
        return "redirect:/employee";
    }
}
