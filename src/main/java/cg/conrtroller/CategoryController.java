package cg.conrtroller;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> showAllCategories(){
        Iterable<Category> categories = categoryService.findAll();
        if (!categories.iterator().hasNext()){
            new ResponseEntity<>(categories, HttpStatus.NO_CONTENT);
        }
        return new  ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<Category> showDetail(@PathVariable("id") Long idC){
        Category category = categoryService.findById(idC);
        if (category==null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @DeleteMapping("{/idC}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("idC") Long idC){
        Category category =  categoryService.findById(idC);
        if (category ==  null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            productService.deleteAllByCategory(category);
            categoryService.deleteById(idC);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
