package com.example.cookingBlog.services;

import com.example.cookingBlog.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image store(MultipartFile file) throws IOException;
    Image getFile(Long id);

//    void setRecipe(Image image, Recipe recipe);

    void setRec(Long recId, Long imgId);
}
