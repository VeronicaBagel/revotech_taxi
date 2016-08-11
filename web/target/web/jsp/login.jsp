<%@ page import ="java.util.ResourceBundle" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization" var="lang"/>

    <fmt:setBundle basename="page_titles" var="page_titles"/>


    <fmt:message bundle="${lang}" key="locale.log_in" var="log_in"/>
    <fmt:message bundle="${lang}" key="locale.register_question" var="register_question"/>
    <fmt:message bundle="${lang}" key="locale.register_proposing" var="register_proposing"/>
    <fmt:message bundle="${lang}" key="locale.login_label" var="login_label"/>
    <fmt:message bundle="${lang}" key="locale.password_label" var="password"/>
    <fmt:message bundle="${lang}" key="locale.registration" var="registration"/>
    <fmt:message bundle="${lang}" key="locale.name_of_the_project" var = "name_of_the_project"/>




    <fmt:message bundle="${page_titles}" key="page_title.login" var="page_login"/>

    <title>${log_in}</title>
    <link href="css/normalize.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>
<input type="hidden" name="locale" value="de"/>
<div class="main-wrapper">
    <div class="container">
        <div class="wrapper row">
            <div class="col-md-2 col-xs-1 col-sm-2">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="locale" value="de"/>
                    <input type="hidden" name="page" value="${page_login}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="de" src="img/de_flat.png" class="locale_img"/>
                    </button>
                </form>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="locale" value="en"/>
                    <input type="hidden" name="page" value="${page_login}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="us" src="img/uk_flat.png" class="locale_img"/>
                    </button>
                </form>

            </div>

            <div class="col-md-2 col-xs-1 col-sm-2"></div>

            <div class="col-md-4 col-xs-10 col-sm-8">
                <div class="error-message text-center">
                    <c:if test="${not empty errorMessage}"><fmt:message bundle="${lang}" key="${errorMessage}"/></div></c:if>
                <h1 class="text-center title">${name_of_the_project}</h1>
                <form name="loginForm" action="controller" onsubmit="validateForm()" method="POST">
                    <input type="hidden" name="command" value="login"/>

                    <div class="form-group"><input type="text" class="form-control" placeholder="${login_label}" name="username" id="username"  min="4" max="8" pattern="[A-Za-z0-9]*"/></div>

                    <div class="form-group"><input type="password" class="form-control" placeholder="${password}" name="password" id="password" min="4" max="8" pattern="[A-Za-z0-9]*" required/></div>

                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary reg-btn">${log_in}</button>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center white-color">
                            ${register_question} <a href="controller?command=registerPage">${register_proposing}</a>
                        </div>
                    </div>
                </form>
            </div>

            <div class="col-md-4 col-xs-1 col-sm-2"></div>
        </div>
    </div>
</div>
</body>
</html>
