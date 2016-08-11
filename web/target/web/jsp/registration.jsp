<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization" var="lang"/>

    <fmt:setBundle basename="page_titles" var="page_titles"/>

    <fmt:message bundle="${lang}" key="locale.log_in" var="log_in"/>
    <fmt:message bundle="${lang}" key="locale.log_in_question" var="log_in_question"/>
    <fmt:message bundle="${lang}" key="locale.sing_up" var="sign_up"/>
    <fmt:message bundle="${lang}" key="locale.login_label" var="login_label"/>
    <fmt:message bundle="${lang}" key="locale.password_label" var="password"/>
    <fmt:message bundle="${lang}" key="locale.password_confirm" var="password_confirm"/>
    <fmt:message bundle="${lang}" key="locale.email_label" var="email"/>
    <fmt:message bundle="${lang}" key="locale.registration" var="registration"/>
    <fmt:message bundle="${lang}" key="locale.input_username_pattern" var="username_pattern"/>
    <fmt:message bundle="${lang}" key="locale.input_password_pattern" var="password_pattern"/>
    <fmt:message bundle="${lang}" key="locale.name_of_the_project" var = "name_of_the_project"/>

    <fmt:message bundle="${page_titles}" key="page_title.registration" var="page_registration"/>

    <title>${registration}</title>
    <link href="css/normalize.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>
<div class="main-wrapper">
    <div class="container">
        <div class="wrapper row">
            <div class="col-md-2 col-xs-1 col-sm-2">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="locale" value="de"/>
                    <input type="hidden" name="page" value="${page_registration}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="de" src="img/de_flat.png" class="locale_img"/>
                    </button>
                </form>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="locale" value="en"/>
                    <input type="hidden" name="page" value="${page_registration}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="us" src="img/uk_flat.png" class="locale_img"/>
                    </button>
                </form>

            </div>

            <div class="col-md-2 col-xs-1 col-sm-2"></div>

            <div class="col-md-4 col-xs-10 col-sm-8">
                <div class="error-message text-center"> <span id="confirmMessage" class="confirmMessage">
                    <c:if test="${not empty errorMessage}"><fmt:message bundle="${lang}" key="${errorMessage}"/></c:if>
                </div>
                <h1 class="text-center title">${name_of_the_project}</h1>
                <form name="registerForm" action="controller" method="POST" >
                    <input type="hidden" name="command" value="registration"/>

                    <div class="form-group"><input type="text" class="form-control" placeholder="${login_label}" name="username" onkeyup="checkLogin(); return false;" min="6" max="20" required pattern="[A-Za-z0-9]*"/>
                        <p class="help-block">${username_pattern}</p>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="${password}" id="password" name="password" min="4" max="8" pattern="[A-Za-z0-9]*" required/>
                        <p class="help-block">${password_pattern}</p>
                    </div>

                    <div class="form-group"><input type="password" class="form-control" placeholder="${password_confirm}" id="password-confirm" name="password-confirm" onkeyup="checkPass(); return false;"  min="4" max="8" pattern="[A-Za-z0-9]*"  required/></div>

                    <div class="form-group"><input type="email" class="form-control" placeholder="${email}" name="email" required/></div>

                    <div class="row">
                        <div class="col-md-12">
                            <button type="submit" id="signUp" class="btn btn-primary reg-btn">${sign_up}</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center white-color">
                            ${log_in_question} <a href="controller?command=loginPage">${log_in}</a>
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
