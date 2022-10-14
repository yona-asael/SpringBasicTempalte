package com.deloitte.mule.tranning.services;

import com.deloitte.mule.tranning.dto.ProductDTO;
import com.deloitte.mule.tranning.dto.ProductQueryDTO;
import com.deloitte.mule.tranning.enums.Message;
import com.deloitte.mule.tranning.enums.Status;
import com.deloitte.mule.tranning.exception.NotFoundException;
import com.deloitte.mule.tranning.models.Product;
import com.deloitte.mule.tranning.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findProducts(ProductQueryDTO dto) {
        log.info("Find all product  with next filters {}", dto);
        return this.productRepository.findAll(createSpecification(dto));
    }

    @Override
    public Product findProduct(Long id) {
        log.info("Find product by the id: {}", id);
        return this.productRepository.findById(id).
                orElseThrow( () -> new NotFoundException("Product", Message.PRODUCT_NT.getMessage()));
    }

    @Override
    public Product createProduct(ProductDTO dto) {
        log.info("Creating product with the information: {}", dto);
        return this.productRepository.save(
                new Product(
                        dto.getName(),
                        "",
                        dto.getCode(),
                        dto.getPrice(),
                        dto.getQuantity(),
                        dto.getArriveDate(),
                        Status.NORMAL
                )
        );
    }

    @Override
    public Product updateProduct(Long id, ProductDTO dto) {
        log.info("Find the product with id: {} and update the info with: {}", id, dto);
        var product = this.productRepository.findById(id).
                orElseThrow( () -> new NotFoundException("Product", Message.PRODUCT_NT.getMessage()));
        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setQuantity(dto.getQuantity());
        product.setStatus(dto.getStatus());
        product.setSlug("");
        return this.productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        log.info("Delete product with id: {}", id);
        var product = this.productRepository.findById(id).
                orElseThrow( () -> new NotFoundException("Product", Message.PRODUCT_NT.getMessage()));
        product.setStatus(Status.DELETED);
        return this.productRepository.save(product);
    }

    private Specification<Product> createSpecification(ProductQueryDTO dto) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            var conjunction = cb.conjunction();
            if(dto.getName() != null) {
                log.info("Filter product with name: {}", dto.getName() );
                conjunction.getExpressions().add(cb.like(root.get("name"),"%" + dto.getName()+ "%"));
            }
            if(dto.getCode() != null) {
                log.info("Filter product with code: {}", dto.getCode() );
                conjunction.getExpressions().add(cb.like(root.get("code"),"%" + dto.getCode()+ "%"));
            }
            if(dto.getStatus() != null) {
                log.info("Filter product with status: {}", dto.getStatus().getStatus() );
                conjunction.getExpressions().add(cb.equal(root.<Integer>get("status"), dto.getStatus().getStatus()));
            } else
                conjunction.getExpressions().add(cb.notEqual(root.<Integer>get("status"), Status.DELETED.getStatus()));

            if(dto.getArriveDate() != null)  {
                log.info("Filter product with arrive date: {}", dto.getArriveDate().toString() );
                conjunction.getExpressions().add(cb.equal(root.<Date>get("arriveDate"),dto.getArriveDate()));
            }
            return conjunction;
        };
    }
}
