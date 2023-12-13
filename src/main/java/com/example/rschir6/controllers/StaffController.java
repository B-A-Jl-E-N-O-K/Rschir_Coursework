package com.example.rschir6.controllers;


import com.example.rschir6.models.Dish;
import com.example.rschir6.models.Staff;
import com.example.rschir6.models.TypeDish;
import com.example.rschir6.models.TypeStaff;
import com.example.rschir6.services.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping()
    public String getFullStaff(Model model)
    {
        model.addAttribute("stafflist", staffService.getAll());
        return "personal";

    }
    @PostMapping()
    public String saveStaff(String id, String name, String salary, String achievements, String post, Model model){
        if (id.isEmpty() && !name.isEmpty() && !salary.isEmpty()) {
            Staff staff = new Staff(name, Integer.parseInt(salary), TypeStaff.valueOf(post), achievements);
            staffService.save(staff);
            return this.getFullStaff(model);

        }
        else if(!id.isEmpty() && name.isEmpty() && salary.isEmpty()){
            staffService.delete(Integer.parseInt(id));
            return this.getFullStaff(model);

        }
        else if(!id.isEmpty() && (!name.isEmpty() || !salary.isEmpty())){
            if(salary.isEmpty())
            {
                salary = "0";
            }
            Staff staff = new Staff(name, Integer.parseInt(salary), TypeStaff.valueOf(post), achievements);
            staffService.update(Integer.parseInt(id), staff);
            return this.getFullStaff(model);

        }
        else{
            return this.getFullStaff(model);
        }


    }
}
