package cg.service.impl;

import cg.model.Category;
import cg.model.Product;
import cg.repository.IProductRepository;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        if (productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Iterable<Product> findAllCategories(Category category) {
        return productRepository.deleteAllByCategory(category);
    }

    @Override
    public Iterable<Product> findAllByNameContainingAndPriceBetweenAndCategory(String name, Double minPrice, Double maxPrice, Category category) {
        return productRepository.findAllByNameContainingAndPriceBetweenAndCategory(name, minPrice, maxPrice, category);
    }


    @Override
    public Iterable<Product> findAllByNameContainingAndPriceBetween(String name, Double minPrice, Double maxPrice) {
        return productRepository.findAllByNameContainingAndPriceBetween(name, minPrice, maxPrice);
    }

    @Override
    public Iterable<Product> deleteAllByCategory(Category category) {
        return productRepository.deleteAllByCategory(category);
    }
}
