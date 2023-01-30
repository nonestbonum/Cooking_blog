<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<#setting classic_compatible=true>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SignIn</title>
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

    <link href="/css/signIn.css" rel="stylesheet"/>

    <#--    <title><@spring.message 'signIn.page.title'/></title>-->
</head>
<body>
<div class="cont">
    <img src="http://localhost:8080/img/page/blackSmall.jpg">
    <div class="cookingBlog">CookingBlog</div>
    <img class="back" src="http://localhost:8080/img/page/backgroundMain.jpg">
    <img class="back" src="http://localhost:8080/img/page/blackSmall.jpg">

    <form action="/signIn" method="post">
        <table>
            <@spring.bind path='signInForm'/>
            <p class="error">${spring.status.errorMessage}</p>

            <tr>
                <td class="l">
                    <label for="email"><@spring.message 'signIn.form.label.enterEmail'/></label>
                    <#assign i18ToolTip><@spring.message code="signIn.form.placeholder.enterEmail"/></#assign>
                </td>
                <td class="r">
                    <@spring.formInput "signInForm.email"/>
                    <@spring.showErrors "<br>" "error"/>
                </td>
            </tr>
            <tr>
                <td class="l">
                    <label for="password"><@spring.message 'signIn.form.label.enterPassword'/></label>
                    <#assign i18ToolTip><@spring.message code="signIn.form.placeholder.enterPassword"/></#assign>
                </td>
                <td class="r">
                    <@spring.formPasswordInput "signInForm.password"/>
                    <@spring.showErrors "<br>" "error"/>
                </td>
            </tr>
        </table>
        <div class="btns">
            <form>
                <button type="submit">Sign In</button>
            </form>
            <form action="/">
                <button>back</button>
            </form>
        </div>
    </form>
</div>
<p>${fail}</p>
</body>
</html>
