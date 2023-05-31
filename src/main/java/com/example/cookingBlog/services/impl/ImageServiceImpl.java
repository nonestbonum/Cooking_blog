package com.example.cookingBlog.services.impl;

import com.example.cookingBlog.models.Image;
import com.example.cookingBlog.models.Recipe;
import com.example.cookingBlog.repositories.ImageRepository;
import com.example.cookingBlog.repositories.RecipeRepository;
import com.example.cookingBlog.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final RecipeRepository recipeRepository;

    public Image store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.length() > 0) {
            Image image = new Image(fileName, file.getContentType(), file.getBytes());
            return imageRepository.save(image);
        } else return null;
    }

    @Override
    public Image getFile(Long id) {

        return imageRepository.findById(id).get();
    }

    @Override
    public void setRec(Long recId, Long imgId) {

        Recipe recToUp = recipeRepository.getById(recId);
        Image imgToUp = imageRepository.getById(imgId);
        imgToUp.setRecipe(recToUp);
        imageRepository.save(imgToUp);
    }

}
