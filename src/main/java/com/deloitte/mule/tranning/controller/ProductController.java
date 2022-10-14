package com.deloitte.mule.tranning.controller;

import com.deloitte.mule.tranning.dto.ProductDTO;
import com.deloitte.mule.tranning.dto.ProductQueryDTO;
import com.deloitte.mule.tranning.dto.ResponseDTO;
import com.deloitte.mule.tranning.enums.Message;
import com.deloitte.mule.tranning.exception.NoParametersException;
import com.deloitte.mule.tranning.models.Product;
import com.deloitte.mule.tranning.services.ProductService;
import com.deloitte.mule.tranning.services.ResponseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ResponseService responseService;

    @GetMapping(value =  {"/", "/{id}"})
    public ResponseEntity<ResponseDTO<?>> findProducts(
            @PathVariable(required = false) Optional<Long> id, @RequestParam(value = "id", required = false) Optional<Long> Qid,
            ProductQueryDTO query, HttpServletRequest request) {
        if(id.isEmpty() && Qid.isEmpty()) {
            log.info("Getting all products from ip: {}", request.getRemoteAddr());
            return new ResponseEntity<>(
                    this.responseService.createResponse(
                            this.productService.findProducts(query),
                            List.of(Message.PRODUCT_DE.getMessage()), HttpStatus.OK
                    ),
                    HttpStatus.OK
            );
        } else {
            log.info("Find product from ip: {}", request.getRemoteAddr());
            return new ResponseEntity<>(
                    this.responseService.createResponse(
                            this.productService.findProduct(id.or(()-> Qid).orElseThrow(NoParametersException::new)),
                            List.of(Message.PRODUCT_DE.getMessage()), HttpStatus.OK
                    ),
                    HttpStatus.OK
            );
        }
    }


    @PostMapping("/")
    public ResponseEntity<ResponseDTO<Product>> createProduct(@RequestBody ProductDTO dto, HttpServletRequest request) {
        log.info("Creating product from ip: {}", request.getRemoteAddr());
        return new ResponseEntity<>(
                this.responseService.createResponse(
                        this.productService.createProduct( dto),
                        List.of(Message.PRODUCT_DE.getMessage()),
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }

    @PutMapping(value = {"/", "/{id}"})
    public ResponseEntity<ResponseDTO<Product>> updateProduct(
                    @PathVariable(required = false) Optional<Long> id,
                    @RequestParam("id") Optional<Long> Qid,
                    @RequestBody ProductDTO dto,
                    HttpServletRequest request) {
        log.info("Updating product from ip: {}", request.getRemoteAddr());
        return new ResponseEntity<>(
                this.responseService.createResponse(
                        this.productService.updateProduct(id.or(()-> Qid).orElseThrow(NoParametersException::new), dto),
                        List.of(Message.PRODUCT_DE.getMessage()),
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = {"/", "/{id}"})
    public ResponseEntity<ResponseDTO<Product>> deleteProduct(
            @PathVariable Optional<Long> id,
            @RequestParam("id") Optional<Long> Qid,
            HttpServletRequest request) {
        log.info("Delete product from ip: {}", request.getRemoteAddr());
        return new ResponseEntity<>(
                this.responseService.createResponse(
                        this.productService.deleteProduct(id.or(()-> Qid).orElseThrow(NoParametersException::new)),
                        List.of(Message.PRODUCT_DE.getMessage()),
                        HttpStatus.OK
                ),
                HttpStatus.OK
        );
    }
}
