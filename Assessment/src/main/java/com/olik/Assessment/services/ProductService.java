package com.olik.Assessment.services;

import com.olik.Assessment.co.GetProductCO;
import com.olik.Assessment.dto.ProductDTO;
import com.olik.Assessment.entities.Category;
import com.olik.Assessment.entities.Product;
import com.olik.Assessment.repositories.CategoryRepository;
import com.olik.Assessment.repositories.ProductRepository;
import com.olik.Assessment.repositories.RentalBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    public List<Product> getAllProducts(GetProductCO getProductCO) {
        Optional<Category> category = categoryRepository.findById(getProductCO.getCategoryId());
        List<ProductDTO> products = new ArrayList<>();
        List<Product> resultList = new ArrayList<>();
        if (category.isPresent()) {
            Long categoryId = category.get().getId();
            String queryString = "SELECT p.* FROM product p " +
                    "JOIN rental_booking rj ON p.id = rj.product_id " +
                    "JOIN product_category pc ON p.id = pc.product_id " +
                    "WHERE pc.category_id = :categoryId " +
                    "AND (:myStartDate not between  rj.start_date  AND rj.end_date) " +
                    "AND (:myEndDate not between  rj.start_date  AND rj.end_date)";

            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter("categoryId", categoryId);
            query.setParameter("myStartDate", getProductCO.getStartDate());
            query.setParameter("myEndDate", getProductCO.getEndDate());
            resultList = query.getResultList();
        }
        return resultList;
    }


    private List<ProductDTO> productDTOList(List<Product> productsList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productsList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setImage(product.getImage());
            productDTO.setName(product.getName());
            productDTO.setCategory(product.getCategories().toString());
            productDTO.setCostPerHour(product.getCostPerHour());
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
}
