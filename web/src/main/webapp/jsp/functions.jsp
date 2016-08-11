<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization" var="lang"/>

    <fmt:setBundle basename="page_titles" var="page_titles"/>
    <fmt:message bundle="${page_titles}" key="page_title.functions" var = "page_functions"/>


    <fmt:message bundle="${lang}" key="locale.main_page" var="main_page"/>
    <fmt:message bundle="${lang}" key="locale.current_options" var="current_options"/>
    <fmt:message bundle="${lang}" key="locale.no_options" var="no_options"/>
    <fmt:message bundle="${lang}" key="locale.back" var="back"/>
    <fmt:message bundle="${lang}" key="locale.log_out" var="log_out"/>
    <fmt:message bundle="${lang}" key="locale.name_of_the_project" var = "name_of_the_project"/>
    <title>${main_page}</title>
    <link href="css/normalize.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>
<div class="main-wrapper">
    <div class="container">
       <div class="wrapper row">
            <div class="col-md-2 col-xs-1 col-sm-2">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="customer_options" value="true" />
                    <input type="hidden" name="locale" value="de"/>
                    <input type="hidden" name="page" value="${page_functions}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="de" src="img/de_flat.png" class="locale_img"/>
                    </button>
                </form>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="customer_options" value="true" />
                    <input type="hidden" name="locale" value="en"/>
                    <input type="hidden" name="page" value="${page_functions}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="us" src="img/uk_flat.png" class="locale_img"/>
                    </button>
                </form>

            </div>


            <div class="col-md-8 col-xs-10 col-sm-8">
                <div class="error-message text-center"> </div>
                <h1 class="text-center title">${name_of_the_project}</h1>
                <c:choose>
                    <c:when test="${not empty functions}">
                        <h2 class="text-center">${current_options}</h2>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-4">
                                <c:forEach items="${functions}" var="item">
                                    <div>${item}</div>
                                </c:forEach>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h2 class="text-center">${no_options}</h2>
                    </c:otherwise>
                </c:choose>

                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <input type="button" class="btn btn-primary reg-btn" onclick="goBack()" value="${back}"/>
                    </div>
                    <div class="col-md-4"></div>
                </div>

            </div>

            <div class="col-md-2 col-xs-1 col-sm-2 row">
                <h3 class="text-right"><c:out value="${customer_login}" /></h3>
                <a href="controller?command=logout" class="logout-link">${log_out}</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
