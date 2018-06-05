<%--
  Created by IntelliJ IDEA.
  User: Rhuan
  Date: 25/05/2018
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<header>
    <title>Servidor Apache Tomcat 9</title>
    <style type="text/css">

        body{
            background-color: #7b68ee;
            height: 100%;
            min-height: 100%;
            height: 100%;
            min-height: 100%;
            display: -webkit-flex;
            display: flex;
            -webkit-align-items: center;
            align-items: center;
            -webkit-justify-content: center;
            justify-content: center;
        }



        .wrapper .box{
            /*background-color: #336E7B;*/
            text-align: center;
            padding: 25px;
        }

        .box > img{
            width: 200px;
        }
        .rodape{
            background-color: white;
            position: absolute;
            bottom: 0;
            width: 100%;
            text-align: center;

        }

    </style>
</header>
<body>

<div class="box">
    <h2>Normas</h2>
</div>
<spring:eval expression="@environment.getProperty('server.versao')" var="versao"/>
<div class="rodape"> V 1.0 - Labtime </div>
</body>
</html>

