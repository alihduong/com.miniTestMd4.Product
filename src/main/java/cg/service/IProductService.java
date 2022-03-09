package cg.service;

import cg.model.Category;
import cg.model.Product;

public interface IProductService {
    Iterable<Product> findAll();

    Product findById(Long id);

    void deleteById(Long id);

    Product save(Product product);

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    Iterable<Product> findAllCategories(Category category);

    Iterable<Product> findAllByNameContainingAndPriceBetweenAndCategory(String name, Double minPrice, Double maxPrice, Category category);

    Iterable<Product> findAllByNameContainingAndPriceBetween(String name, Double minPrice, Double maxPrice );

    Iterable<Product> deleteAllByCategory(Category category);
}
