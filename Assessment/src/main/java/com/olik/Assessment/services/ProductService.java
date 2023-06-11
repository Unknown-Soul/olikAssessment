package com.olik.Assessment.services;

import com.olik.Assessment.co.GetProductCO;
import com.olik.Assessment.dto.ProductDTO;
import com.olik.Assessment.entities.Category;
import com.olik.Assessment.entities.Product;
import com.olik.Assessment.repositories.CategoryRepository;
import com.olik.Assessment.repositories.ProductRepository;
import com.olik.Assessment.repositories.RentalBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RentalBookingRepository rentalBookingRepository;
    @PersistenceContext
    private EntityManager entityManager;


    public List<ProductDTO> getAllProducts(GetProductCO getProductCO) {
        Optional<Category> category = categoryRepository.findById(getProductCO.getCategoryId());
        List<Product> resultList = new ArrayList<>();

        if (category.isPresent()) {
            Long categoryId = category.get().getId();
            String queryString = "SELECT DISTINCT p.* FROM product p " +
                    "LEFT JOIN rental_booking rj ON p.id = rj.product_id " +
                    "LEFT JOIN product_category pc ON p.id = pc.product_id " +
                    "WHERE pc.category_id = :categoryId " +
                    "AND ( " +
                    "NOT EXISTS (SELECT 1 FROM rental_booking rb " +
                    "WHERE rb.product_id = p.id " +
                    "AND (rb.start_date BETWEEN :myStartDate AND :myEndDate OR " +
                    "rb.end_date BETWEEN :myStartDate AND :myEndDate)))";

            Query query = entityManager.createNativeQuery(queryString, Product.class);
            query.setParameter("categoryId", categoryId);
            query.setParameter("myStartDate", getProductCO.getStartDate());
            query.setParameter("myEndDate", getProductCO.getEndDate());
            resultList = query.getResultList();
            productDTOList(resultList);
        }
        return productDTOList(resultList);
    }


    private List<ProductDTO> productDTOList(List<Product> productsList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productsList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setImage(product.getImage());
            productDTO.setName(product.getName());
            productDTO.setCategory(product.getCategories().toString());
            productDTO.setCostPerHour(product.getCostPerHour());
            productDTO.setCostPerWeek(product.getCostPerWeek());
            productDTO.setCostPerDay(product.getCostPerDays());
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
}
