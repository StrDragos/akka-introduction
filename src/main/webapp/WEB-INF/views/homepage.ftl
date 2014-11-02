<#import "spring.ftl" as spring />

<!DOCTYPE >
<html>
<body>
<@spring.bind "pageModel" />
<form name="model" method="POST" action="/countwords">
    <label for="frase">Your statment</label>
<#--<input id="frase" name="countFor" type="text"  value="${pageModel}">-->
<@spring.formInput "pageModel.countFor" />
<@spring.formTextarea "pageModel.countedWords"/>
    <p>${pageModel}</p>
    <input type="submit" value="Send">
    <input type="submit" value="Get result" formmethod="GET" formaction="/results">
</form>
</body>

</html>