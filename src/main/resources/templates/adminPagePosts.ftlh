<!DOCTYPE html>
<#setting classic_compatible=true>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
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

    <#--    совсем тонкий-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Alumni+Sans+Pinstripe&family=Big+Shoulders+Inline+Display:wght@900&family=Montserrat:wght@700&display=swap"
          rel="stylesheet">
    <link href="/css/startPageSignIn.css" rel="stylesheet"/>

</head>
<body>
<div class="cont">

    <img src="http://localhost:8080/img/page/blackSmall.jpg">
    <div class="search">
        <form class="example" method="post" action="/search">
            <input type="text" placeholder="поиск..." name="search">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <div class="logout">
        <form action="/logout">
            <button>выход</button>
        </form>
    </div>
    <div class="cookingBlog">CookingBlog</div>
    <img class="back" src="http://localhost:8080/img/page/backgroundMain.jpg">
    <img class="back" src="http://localhost:8080/img/page/blackSmall.jpg">
</div>

<div class="top6">
    <h1>ADMIN_POSTS-BY-BLOG: ${title}</h1>
</div>
<div class="posts">
    <table style="width: 100%">
        <tr>
            <td> id</td>
            <td> date</td>
            <td> image</td>
            <td> изменить фото </td>
            <td> title</td>
            <td></td>
        </tr>
        <#list recipes as recipe>
            <tr>
                <form action="/accounts/${recipe.getBlog().id}/${recipe.id}/changePost" method="post" enctype="multipart/form-data">
                    <td>${recipe.id}</td>
                    <td>${recipe.dateOfCreate}</td>
                    <td><img src="/images/${recipe.id}" height="150px"/></td>
                    <td><input name="photo" type="file"/></td>
                    <td><textarea name="title" rows="1">${recipe.title}</textarea></td>
                    <td><button type="submit">принять изменения</button></td>
                </form>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>