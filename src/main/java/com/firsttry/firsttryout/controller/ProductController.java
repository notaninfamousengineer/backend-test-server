package com.firsttry.firsttryout.controller;

import com.firsttry.firsttryout.model.Products;
import com.firsttry.firsttryout.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private List<Products> productsList = new ArrayList<>(List.of(
            new Products(1, "Coffee", 18.50F),
            new Products(2, "Tea", 13.99F),
            new Products(3, "Biscuits", 3.99F),
            new Products(4, "Milkshake", 20.00F)
    ));


    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to Spring Coffee Center";
    }

    @RequestMapping("list") // This maps to the URL http://localhost:8080/products/list
    public String listProducts(Model productListModel) { // Model argument is used to pass data to the view
        productListModel.addAttribute("products", productsList); // Add the productsList to the model
        return "menu";  // This returns the view name, that is, the JSP file name
    }


    @RequestMapping("details/{id}")
    @ResponseBody
    public String getProductDetails(@PathVariable int id) {

        for (Products p : productsList) {
            if(p.getProductId() == id) {
                return "Product ID : " + p.getProductId() + ", Product name" + p.getProductName() + " ,Price :" + p.getProductPrice();
            }
        }

        return "Item not found";
    }

    @RequestMapping("addproduct")
    public String addProduct(Model productListModel) {
//        productListModel.addAttribute("productsList", productsList);
        Products product = new Products();
        productListModel.addAttribute("product", product);
        return "addProduct";
    }

    @PostMapping("saveproduct")
    public String saveProduct(@ModelAttribute("product") Products product) {
        productsList.add(product);
        return "redirect:/products/list";
    }

}
