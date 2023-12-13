package com.example.rschir6.controllers;

import com.example.rschir6.models.Dish;
import com.example.rschir6.models.TypeDish;
import com.example.rschir6.services.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
@Controller
@RequestMapping("/")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping()
    public String getFullMenu(Model model)
    {
        model.addAttribute("dishlist", dishService.getAll());
        this.hideSystem(model);
        return "menu";

    }


    @PostMapping()
    public String saveData(String id, String name, String price, String type, String is_available, String structure, String image, Model model){
        if(id.isEmpty() && name.isEmpty() && !type.equals("NONE")){
            model.addAttribute("dishlist", dishService.getFilteredMenu(type));
            this.hideSystem(model);
            return "menu";
        }
        else if (id.isEmpty() && !name.isEmpty() && !price.isEmpty()) {
            Dish dish = new Dish(name, Integer.parseInt(price), TypeDish.valueOf(type), Boolean.parseBoolean(is_available), structure, image);
            dishService.save(dish);
            return this.getFullMenu(model);

        }
        else if(!id.isEmpty() && name.isEmpty() && price.isEmpty()){
            dishService.delete(Integer.parseInt(id));
            return this.getFullMenu(model);

        }
        else if(!id.isEmpty() && (!name.isEmpty() || !price.isEmpty())){
            if(price.isEmpty())
            {
                price = "0";
            }
            Dish dish = new Dish(name, Integer.parseInt(price), TypeDish.valueOf(type), Boolean.parseBoolean(is_available), structure, image);
            dishService.update(Integer.parseInt(id), dish);
            return this.getFullMenu(model);

        }
        else{
            return this.getFullMenu(model);
        }


    }

    public void hideSystem(Model model) {

        model.addAttribute("isAdmin", AuthController.isAdmin);

    }

    /*
    public Document makeTable(String inpType) throws IOException {

        File file;
        Document document;
        Element table;
        file = new File("d:/IT_Works_Art/Rschir/CourseWork/src/main/resources/templates/menu.html");
        document = Jsoup.parse(file);
        table = document.getElementById("table-cont-id");
        /*
        try{
            file = new File("d:/IT_Works_Art/Rschir/CourseWork/src/main/resources/templates/menu.html");
            document = Jsoup.parse(file);
            table = document.getElementById("table-cont-id");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        */
    /*
        if(!table.hasClass("table-content")){
            throw new RuntimeException("Табличка меню не нейдена!");
        }

        /*
        Element addForm = new Element("form");
        addForm.attr("name", "addForm");
        addForm.attr("id", "addForm");
        addForm.attr("method", "post");
        addForm.attr("action", "");
        Element add = new Element("button");
        add.attr("name", "formButton");
        add.attr("type", "submit");
        */
/*
        int counter = 1;
        Iterable<Dish> dishList = dishService.getAll();
        for (Dish dish: dishList)
        {
            if(inpType == dish.getType().toString() || inpType == "NONE")
            {
                Element row = new Element("div");
                row.addClass("table-row");

                Element cell_count = new Element("div");
                cell_count.addClass("table-data");
                cell_count.appendText(Integer.toString(counter));
                row.appendChild(cell_count);

                Element cell_name = new Element("div");
                cell_name.addClass("table-data");
                cell_name.appendText(dish.getName());
                row.appendChild(cell_name);

                Element cell_type = new Element("div");
                cell_type.addClass("table-data");
                cell_type.appendText(dish.getType().toString());
                row.appendChild(cell_type);

                Element cell_price = new Element("div");
                cell_price.addClass("table-data");
                cell_price.appendText(Integer.toString(dish.getPrice()));
                row.appendChild(cell_price);




                /*
                cell.empty();
                cell.appendText(dish.getType().toString());
                row.appendChild(cell);
                cell.empty();
                cell.appendText(Integer.toString(dish.getPrice()));
                row.appendChild(cell);
                cell.empty();*/
                /*
                add.attr("value", Integer.toString(dish.getId()));
                cell.appendChild(addForm);
                row.appendChild(cell);
                cell.empty();
                */
/*
                table.appendChild(row);

                counter++;
            }
        }
        //System.out.println(document.html());
        System.out.println("***");
        System.out.println("***");
        System.out.println("***");
        String html = document.html();
        Writer writer = new PrintWriter("d:/IT_Works_Art/Rschir/CourseWork/src/main/resources/templates/menu.html", html);
        writer.write(html);
        writer.close();
        return document;
    }*/
}
