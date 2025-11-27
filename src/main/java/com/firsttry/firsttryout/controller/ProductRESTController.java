package com.firsttry.firsttryout.controller;


import com.firsttry.firsttryout.model.Products;
import com.firsttry.firsttryout.model.Urls;
import com.firsttry.firsttryout.service.ProductService;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRESTController {

    ProductService productServices = new ProductService();


    private List<Products> productsList = new ArrayList<>(List.of(
            new Products(1, "Coffee", 18.50F),
            new Products(2, "Tea", 13.99F),
            new Products(3, "Biscuits", 3.99F),
            new Products(4, "Milkshake", 20.00F)
    ));

    @GetMapping("/")
    public ResponseEntity<List<Products>> getAllProducts() {

        return ResponseEntity.ok(productsList);

    }

//    @GetMapping("url")
//    public ResponseEntity<Urls> getAllProductsByURL(@RequestBody Urls rcvdURL ) {
//
//        Urls newObj = new Urls(rcvdURL.getLongUrl(), "youtube.com");
//
//        return ResponseEntity.ok(newObj);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable int id) {

        Products product = productServices.findProductById(id, productsList);

        return ResponseEntity.ok(product);

    }

}
