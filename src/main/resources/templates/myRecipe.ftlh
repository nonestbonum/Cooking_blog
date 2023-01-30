<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${recipe_title}</title>
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

    <link href="/css/oneRecipe.css" rel="stylesheet"/>
</head>
<body>
<div class="back">
    <div class="goBack">
        <form action="/${user_id}/blog">
            <button>назад</button>
        </form>
    </div>
    <img src="http://localhost:8080/img/page/blackSmall.jpg">
    <div class="title">
        ${blog_title}
    </div>
</div>
<div class="all">
    <div class="recName">
        ${recipe_title}
    </div>
    <br>
    <div class="photo">
        <img src="/images/${recipe_id}" height="400vw"/>
    </div>
    <br>
    <div class="recipe_text">
        ${recipe_text}
    </div>
    <br>
    <div class="del">
        <form action="/${user_id}/blog/myRecipe/${recipe_id}/delete" method="post">
            <button>
                удалить рецепт
            </button>
        </form>
    </div>
    <div class="del">
        <form action="/${user_id}/blog/myRecipe/${recipe_id}/update">
            <button>
                редактировать рецепт
            </button>
        </form>
    </div>
    <#list comments as comment>
        <div class="comments">
            <div class="blogT">${comment.blog_title}</div>
            <div class="commT">${comment.comment_text}</div>
            <br>
        </div>
    </#list>
</div>

</div>

</body>
</html>