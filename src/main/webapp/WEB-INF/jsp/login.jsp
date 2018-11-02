<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/css/uikit.min.css" />
</head>
<body>

<style>
.form-auth {

    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

}
html, body {

    width: 100%;
    height: 80%;

}
</style>
<h1 class="uk-heading-line uk-text-center"><span>Log in</span></h1>
<div class="form-auth">
	<form method="POST" action="/student-app/login">
	<span class="uk-text-danger">${error}</span>
		<div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: user"></span>
	            <input class="uk-input name" required="required" placeholder="Name" name="name" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: lock"></span>
	            <input class="uk-input surname" required="required" placeholder="PAssword" name="password" type="password">
	        </div>
	    </div>
		<button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom">Add student</button>
	</form>
</div>
<!-- UIkit JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/js/uikit.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/js/uikit-icons.min.js"></script>

</body>
</html>