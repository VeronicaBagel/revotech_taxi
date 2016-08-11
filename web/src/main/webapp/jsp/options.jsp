<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization" var="lang"/>

    <fmt:setBundle basename="app_properties" var="prop"/>
    <fmt:message bundle="${prop}" key="prop.column_number" var="column_number"/>


    <fmt:setBundle basename="page_titles" var="page_titles"/>
    <fmt:message bundle="${page_titles}" key="page_title.options" var = "page_options"/>

    <fmt:message bundle="${lang}" key="locale.main_page" var="main_page"/>
    <fmt:message bundle="${lang}" key="locale.choice_proposition" var="choice_proposition"/>
    <fmt:message bundle="${lang}" key="locale.choose_button" var="choose_button"/>
    <fmt:message bundle="${lang}" key="locale.choice_confirmation" var="choice_confirmation"/>
    <fmt:message bundle="${lang}" key="locale.cancel" var="cancel"/>
    <fmt:message bundle="${lang}" key="locale.yes" var="yes"/>
    <fmt:message bundle="${lang}" key="locale.no_access" var="no_access"/>
    <fmt:message bundle="${lang}" key="locale.registration_proposition" var="register_proposition"/>
    <fmt:message bundle="${lang}" key="locale.log_out" var="log_out"/>
    <fmt:message bundle="${lang}" key="locale.name_of_the_project" var = "name_of_the_project"/>


    <title>${main_page}</title>
    <link href="css/normalize.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
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
                    <input type="hidden" name="all_options" value="true" />
                    <input type="hidden" name="locale" value="de"/>
                    <input type="hidden" name="page" value="${page_options}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="de" src="img/de_flat.png" class="locale_img"/>
                    </button>
                </form>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="all_options" value="true" />
                    <input type="hidden" name="locale" value="en"/>
                    <input type="hidden" name="page" value="${page_options}"/>
                    <button type="submit" class="locale_link" style="border: 0; background: transparent">
                        <img alt="us" src="img/uk_flat.png" class="locale_img"/>
                    </button>
                </form>

            </div>

            <div class="col-md-8 col-xs-10 col-sm-8">
                <div class="error-message text-center"> </div>
                <h1 class="text-center title">${name_of_the_project}</h1>
                <c:choose>
                    <c:when test="${not empty customer_login}">
                        <h1 class="text-center">${choice_proposition}</h1>
                        <form class="functionForm row" action="controller" method="POST">
                    <input type="hidden" name="command" value="chooseFunctions"/>

                    <div class="row">


                            <c:forEach items="${options}" var="option">
                                <div class="col-md-${column_number}">
                                    <div class="form-group">
                                        <input type="checkbox" id="${option.id}" name="option" value="${option.id}"
                                        <c:forEach var="item" items="${checked_options}">
                                            <c:if test="${item==option.id}">
                                               checked
                                            </c:if>
                                        </c:forEach>
                                        >
                                       <label for="${option.id}">${option.label}</label>
                                    </div>
                                </div>
                            </c:forEach>


                    </div>

                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-4"><button type="button" class="btn btn-primary reg-btn" data-toggle="modal" data-target="#myModal">${choose_button}</button></div>
                        <div class="col-md-4"></div>
                    </div>

                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        </div>
                                        <div class="modal-body">
                                           <p class="text-center black-font">${choice_confirmation}</p>
                                            <div class="modal-footer">
                                                <div class="row">
                                                    <input type="button" class="btn btn-primary modal-btn" value="${cancel}" data-dismiss="modal"/>
                                                    <input type="submit" class="btn btn-primary modal-btn" value="${yes}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                </form>
                    </c:when>
                    <c:otherwise>
                        <p>${no_access}
                            <a href="controller?command=registerPage">${register_proposition}</a></p>
                    </c:otherwise>
                </c:choose>

            </div>

            <div class="col-md-2 col-xs-1 col-sm-2 row">
                <h3 class="text-right"><c:out value="${customer_login}" /></h3>
                <a href="controller?command=logout" class="logout-link">${log_out}</a>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
