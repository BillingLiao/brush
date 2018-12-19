package com.shokey.brushadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping("/pages/ui-features/buttons.html")
    public String button(){
        return "pages/ui-features/buttons";
    }

    @RequestMapping("/pages/ui-features/typography.html")
    public String typography(){
        return "pages/ui-features/typography";
    }

    @RequestMapping("/pages/icons/mdi.html")
    public String mid(){
        return "pages/icons/mdi";
    }

    @RequestMapping("/pages/forms/basic_elements.html")
    public String basic_elements(){
        return "pages/forms/basic_elements";
    }

    @RequestMapping("/pages/charts/chartjs.html")
    public String chartjs(){
        return "pages/charts/chartjs";
    }

    @RequestMapping("/pages/tables/basic-table.html")
    public String table(){
        return "pages/tables/basic-table";
    }

    @RequestMapping("pages/samples/blank-page.html")
    public String blank(){
        return "pages/samples/blank-page";
    }

    @RequestMapping("/pages/samples/error-404.html")
    public String error(){
        return "pages/samples/error-404";
    }

    @RequestMapping("/pages/samples/error-500.html")
    public String errors(){
        return "pages/samples/error-500";
    }
}
