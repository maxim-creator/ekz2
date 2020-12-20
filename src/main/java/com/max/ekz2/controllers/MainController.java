package com.max.ekz2.controllers;

import com.max.ekz2.models.Product;
import com.max.ekz2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class MainController {
    final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String mainPage(Model model){
        Product product = new Product();
        model.addAttribute("newProduct", product);
        model.addAttribute("allProducts",  productService.findAll());
        return "mainPage";
    }

    @PostMapping("/newProduct")
    public  String newProduct(@ModelAttribute("newProduct") @Valid Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "mainPage";
        productService.save(product);
        return "redirect:";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        productService.deleteById(id);
        return "redirect:../";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "edit";
        product.setId(id);
        productService.edit(product);
        return "redirect:../";
    }


}
