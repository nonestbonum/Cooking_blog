<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<#setting classic_compatible=true>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>New Recipe</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>CookingBlog</title>
    <#--    тонкий потолще font-family: 'Alumni Sans', sans-serif;-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Alumni+Sans:wght@300&display=swap" rel="stylesheet">

    <#--    CookingBlog-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Big+Shoulders+Inline+Display:wght@900&family=Montserrat:wght@700&display=swap"
          rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link href="/css/newRecipe.css" rel="stylesheet"/>

</head>
<body>
<div class="back">
    <div class="goBack">
        <form action="/${blog_id}/blog">
            <button>назад</button>
        </form>
    </div>
    <img src="http://localhost:8080/img/page/blackSmall.jpg">
    <div class="title">
        ${blog_title}
    </div>
</div>
<div>
    <form action="/${blog_id}/blog/create" method="post" enctype="multipart/form-data">
        <div class="newRecipe">
            <button type="submit">
                создать рецепт
            </button>
        </div>
        <div class="creation">
            <div class="recName">
                <input type="text" name="title" placeholder="Название рецепта"/>
            </div>
            <br>
            <br>
            <div class="file_input_div">
                <button>добавить фото</button>
                <input type="file" name="file" class="file_input_hidden"/>
            </div>
            <br>
            <br>
            <div>
                <textarea class="recText" rows="1" name="recipe_text" placeholder="рецепт"> </textarea>
            </div>
        </div>
    </form>
</div>
<div class="fail">
    <p>${fail}</p>
</div>
</body>
</html>