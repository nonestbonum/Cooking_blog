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

    <link href="/css/oneRecipeSignIn.css" rel="stylesheet"/>
</head>
<body>
<div class="back">
    <div class="goBack">
        <form action="/${user_id}">
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
    <div class="photo">
        <img src="/images/${recipe_id}" height="400vw"/>
    </div>

    <div class="recipe_text">
        ${recipe_text}
    </div>
    <div class="rating">
        <form action="/${user_id}/oneRecipe/${recipe_id}" method="post">
            <div class="form-group">
                <label>Ваша оценка</label>
                <br>
                <div class="star-rating">
                    <div class="star-rating__wrap">
                        <br>
                        <input class="star-rating__input" id="star-5" type="radio" name="rating" value="5">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-5" title="Отлично"></label>
                        <input class="star-rating__input" id="star-4" type="radio" name="rating" value="4">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-4" title="Хорошо"></label>
                        <input class="star-rating__input" id="star-3" type="radio" name="rating" value="3">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-3"
                               title="Удовлетворительно"></label>
                        <input class="star-rating__input" id="star-2" type="radio" name="rating" value="2">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-2" title="Плохо"></label>
                        <input class="star-rating__input" id="star-1" type="radio" name="rating" value="1">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-1" title="Ужасно"></label>
                    </div>
                </div>
            </div>
            <br>
            <button type="submit">оценить</button>
        </form>
    </div>
    <div class="yourComment">
        <form class="yourComment" action="/${user_id}/oneRecipe/${recipe_id}/comment" method="post">
            <textarea class="recText" rows="1" name="comment_text" placeholder="Ваш комментарий"></textarea>
            <button type="submit">отправить</button>
        </form>
    </div>
    <#--    <div class="comments">-->
    <#list comments as comment>
        <div class="comments">
            <div class="blogT">${comment.blog_title}</div>
            <div class="commT">${comment.comment_text}</div>
            <br>
        </div>
    </#list>
    <#--    </div>-->

</div>


</body>
</html>