<#import "spring.ftl" as spring />

<!DOCTYPE >
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Introduction to AKKA Framework</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container, jumbotron">
    <div class="row">
        <@spring.bind "pageModel" />
        <form name="model" method="POST" action="/countwords">
            <label for="frase">Your statment</label>
            <textarea class="col-lg-12" rows="10" name="countFor"><#if pageModel.countFor??>${pageModel.countFor}</#if></textarea>

            <div class="col-lg-12"><p><#if pageModel.countedWords??>${pageModel.countedWords}</#if></p></div>
            <div class="col-lg-12">
                <input type="submit" value="Send">
                <input type="submit" value="Get result" formmethod="GET" formaction="/results">
            </div>
        </form>
    </div>
</div>
</body>

</html>