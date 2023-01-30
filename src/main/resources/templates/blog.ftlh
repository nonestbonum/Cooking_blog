<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <title>MyBlog</title>
    <meta charset="UTF-8">
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

    <link href="/css/blog.css" rel="stylesheet"/>

</head>
<body>
<div class="back">
    <div class="goBack">
        <form action="/${blog_id}">
            <button>назад</button>
        </form>
    </div>
    <div class="changeUsername">
        <form action="/${blog_id}/blog/changeUsername">
            <button>изменить ник</button>
        </form>
        <form action="/${blog_id}/blog/clearBlog" method="post">
            <button>очистить блог</button>
        </form>
    </div>
    <img src="http://localhost:8080/img/page/blackSmall.jpg">
    <div class="title">
        ${blog_title}
    </div>
</div>

<div class="newRecipe">
    <form action="/${blog_id}/blog/create">
        <button>добавить новый рецепт</button>
    </form>
</div>
<br>
<br>
<br>
<br>
<div class="recipes">
    <section>
        <#list recipes as recipe>
            <article>
                <div><a href="/${userId}/blog/myRecipe/${recipe.id}"><img src="/images/${recipe.id}" height="300px"/></a><br></div>
                <h2>${recipe.title}</h2>
                <#assign x = recipe.rating>
                <h3>${x?string["0.##"]}</h3>
            </article>
        </#list>
    </section>
</div>
</body>



