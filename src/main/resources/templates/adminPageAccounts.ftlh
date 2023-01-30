<!DOCTYPE html>
<#setting classic_compatible=true>
<#import "spring.ftl" as spring/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>admin</title>
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
    <#--    <div class="signIn" id="signIn">-->
    <img class="back" src="http://localhost:8080/img/page/blackSmall.jpg">
</div>

<div class="top6">
    <h1>ADMIN</h1>
</div>
<div class="recipes">
    <table style="width: 100%">
        <tr>
            <td>id</td>
            <td>email</td>
            <td>role</td>
            <td>blog title</td>
            <td></td>
            <td>is banned</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <#list accounts as account>
            <tr>
                <form action="/accounts/${account.id}/change" method="post">
                    <td>${account.id}</td>
                    <td><textarea class="accountEmail" rows="1" name="accountEmail">${account.email}</textarea></td>
                    <td><select name="accountRole">
                            <option selected value="${account.role}">${account.role}</option>
                            <#if account.role =="ADMIN">
                                <option value="USER">USER</option>
                            <#else>
                                <option value="ADMIN">ADMIN</option>
                            </#if>
                        </select>
                    </td>
                    <td>
                        <textarea class="account_blogName" rows="1"
                                  name="account_blogName">${account.getBlog().title}</textarea>
                    </td>
                    <td>
                        <button type="submit">принять изменения</button>
                    </td>
                </form>
                <td>${account.isBanned()?string}</td>
                <td>
                    <#if account.isBanned()?string == "false">
                        <form action="/accounts/${account.id}/ban" method="post">
                            <button>забанить</button>
                        </form>
                    <#else>
                        <form action="/accounts/${account.id}/unBan" method="post">
                            <button>разбанить</button>
                        </form>
                    </#if>

                </td>
                <td>
                    <form action="/accounts/${account.id}/posts">
                        <button>посты</button>
                    </form>
                </td>
                <td>
                    <form action="/accounts/${account.id}/delete" method="post">
                        <button>удалить</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>