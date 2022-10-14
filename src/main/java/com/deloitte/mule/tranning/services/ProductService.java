package com.deloitte.mule.tranning.services;

import com.deloitte.mule.tranning.dto.ProductDTO;
import com.deloitte.mule.tranning.dto.ProductQueryDTO;
import com.deloitte.mule.tranning.models.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findProducts(ProductQueryDTO dto);

    public Product findProduct(Long id);

    public Product createProduct(ProductDTO dto);

    public Product updateProduct(Long id, ProductDTO dto);

    public Product deleteProduct(Long id);

}
