<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<#setting classic_compatible=true>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ChangeUsername</title>
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

    <link href="/css/changeUsername.css" rel="stylesheet"/>
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
<div class="change">
    <form action="/${blog_id}/blog/changeUsername" method="post">
        <input type="text" name="newTitle" placeholder="Новое название блога"/>
        <br>
        <button type="submit">
            изменить
        </button>
    </form>
</div>
<div class="fail">
    <p>${fail}</p>
</div>
</body>
</html>