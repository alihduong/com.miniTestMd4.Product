package cg.repository;

import cg.model.Category;
import cg.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);

    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByNameContainingAndPriceBetween(String name, Double minPrice, Double maxPrice);

    Iterable<Product> deleteAllByCategory(Category category);

    Iterable<Product> findAllByNameContainingAndPriceBetweenAndCategory(String name, Double minPrice, Double maxPrice, Category category);
}
