package com.firsttry.firsttryout.service;


import com.firsttry.firsttryout.model.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public Products findProductById(int id, List<Products> productsList) {

        for (Products p : productsList) {
            if (p.getProductId() == id) {
                return p;
            }
        }

        return null;

    }

}
