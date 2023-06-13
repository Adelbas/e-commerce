package com.adel.ecommerce.service;

import com.adel.ecommerce.model.Image;
import com.adel.ecommerce.model.Product;
import com.adel.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String name) {
        if (name != null) return productRepository.findByName(name);
        return productRepository.findAll();
    }

    public void saveProduct(Product product, List<MultipartFile> files) throws IOException {
        for(int i=0; i<files.size(); i++){
            if(files.get(i).getSize()!=0){
                Image image = toImageEntity(files.get(i));
                if(i==0)
                    image.setPreviewImage(true);
                product.addImageToProduct(image);
            }
        }
        log.info("Saving new Product: name: {};", product.getName());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getRelatedProducts(Long id) {
        List<Product> relatedList = new ArrayList<>();
        List<Product> products = this.listProducts(null);
        products.removeIf(p->p.getId().equals(id));
        Collections.shuffle(products);
        for(int i=0; i<4; i++) {
            relatedList.add(products.get(i));
        }
        return relatedList;
    }
}