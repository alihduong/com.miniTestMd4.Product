package cg.conrtroller;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("{id}")
    public ResponseEntity<Iterable<Product>> showAll(){
        Iterable<Product> products = productService.findAll();
        if (!products.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showDetail(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        if (product == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product ,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable("id") Long id, @RequestBody Product product){
        Product product1 = productService.findById(id);
        if(product1 == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product product = productService.findById(id);
        if (product == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> findAllByNameContaining(@RequestParam("search") String search, @RequestParam("minPrice") String minPrice, @RequestParam("maxPrice") String maxPrice, @RequestParam("idC") Long idC){
        Iterable<Product> products = productService.findAllByNameContaining(search);
        if (!products.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/searchFull")
    public ResponseEntity<Iterable<Product>> findAll(@RequestParam("search") String search , @RequestParam("minPrice") String minPrice, @RequestParam("maxPrice") String maxPrice, @RequestParam("idC") Long idC){
        Iterable<Product> products;
        Iterable<Product> productState = productService.findAll();
        double max = 0, min = 0;
        for (Product product: productState) {
            if (product.getPrice() < min){
                min = product.getPrice();
            }
            if (product.getPrice() > max){
                max = product.getPrice();
            }
        }

        if(minPrice.equals("")){
            minPrice = String.valueOf(min);
        }
        if (maxPrice.equals("")){
            maxPrice = String.valueOf(max);
        }
        if(idC == 0){
            products = productService.findAllByNameContainingAndPriceBetween(search, Double.parseDouble(minPrice), Double.parseDouble(maxPrice));
        }else {
            Category category = categoryService.findById(idC);
            products = productService.findAllByNameContainingAndPriceBetweenAndCategory(search,Double.parseDouble(minPrice), Double.parseDouble(maxPrice), category);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
