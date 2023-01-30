<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<#setting classic_compatible=true>
<html lang="en">
<head>
    <title>SignUp</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
    <link href="/css/signUp.css" rel="stylesheet"/>
</head>
<body>
<div class="cont">
    <img src="http://localhost:8080/img/page/blackSmall.jpg">
    <div class="cookingBlog">CookingBlog</div>
    <img class="back" src="http://localhost:8080/img/page/backgroundMain.jpg">
    <img class="back" src="http://localhost:8080/img/page/blackSmall.jpg">

    <form action="/signUp" method="post">
        <table>
            <@spring.bind path='signUpForm'/>
            <p class="error">${spring.status.errorMessage}</p>

            <tr>
                <td>
                    <label for="firstName"><@spring.message 'signUp.form.label.enterFirstName'/></label>
                    <#assign i18ToolTip><@spring.message code="signUp.form.placeholder.enterFirstName"/></#assign>
                </td>
                <td>
                    <@spring.formInput "signUpForm.firstName"/>
                    <@spring.showErrors "<br>" "error"/>
                </td>
                <td>
                    <label for="lastName"><@spring.message 'signUp.form.label.enterLastName'/></label>
                    <#assign i18ToolTip><@spring.message code="signUp.form.placeholder.enterLastName"/></#assign>
                </td>
                <td>
                    <@spring.formInput "signUpForm.lastName"/>
                    <@spring.showErrors "<br>" "error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email"><@spring.message 'signUp.form.label.enterEmail'/></label>
                    <#assign i18ToolTip><@spring.message code="signUp.form.placeholder.enterEmail"/></#assign>
                </td>
                <td>
                    <@spring.formInput "signUpForm.email"/>
                    <@spring.showErrors "<br>" "error"/>
                </td>
                <td>
                    <label for="password"><@spring.message 'signUp.form.label.enterPassword'/></label>
                    <#assign i18ToolTip><@spring.message code="signUp.form.placeholder.enterPassword"/></#assign>
                </td>
                <td>
                    <@spring.formPasswordInput "signUpForm.password"/>
                    <@spring.showErrors "<br>" "error"/>
                </td>
            </tr>
        </table>
        <div class="btns">
            <form>
                <button type="submit">Sign Up</button>
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
