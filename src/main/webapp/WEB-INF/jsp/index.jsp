<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/css/uikit.min.css" />
	<link rel="stylesheet" href="/student-app/WEB-INF/classes/css/main.css" />
</head>
<body>
<style>
.forms > form {

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

}
.forms {

    display: flex;
    flex-direction: row;
    justify-content: space-around;
    width: 60%;
    margin-left: 20%;
    margin-top: 20px;

}

.tables-university__members > table {
    width: 49%;
}
.tables-university__members {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
    align-items: baseline;
}
</style>
<div class="forms">
	<form action="/student-app/students" method="POST">
		<p class="uk-text-uppercase uk-text-center" >Student</p>
		<div class="uk-margin">
		        <div class="uk-inline">
		            <a class="uk-form-icon" href="#" uk-icon="icon: pencil"></a>
		            <input class="uk-input" type="text" name="name">
		        </div>
		    </div>
		
		    <div class="uk-margin">
		        <div class="uk-inline">
		            <a class="uk-form-icon uk-form-icon-flip" href="#" uk-icon="icon: link"></a>
		            <input class="uk-input" type="text" name="age">
		        </div>
		    </div>
			<button class="uk-button uk-button-primary">Primary</button>
	</form>


	<form action="/student-app/students" method="POST">
	<p class="uk-text-uppercase uk-text-center" >Professor</p>
			<div class="uk-margin">
			        <div class="uk-inline">
			            <a class="uk-form-icon" href="#" uk-icon="icon: pencil"></a>
			            <input class="uk-input" type="text" name="name" placeholder="Name">
			        </div>
			    </div>
			    
			    <div class="uk-margin">
			        <div class="uk-inline">
			            <a class="uk-form-icon uk-form-icon-flip" href="#" uk-icon="icon: link"></a>
			            <input class="uk-input" type="text" name="age" placeholder="Age">
			        </div>
			    </div>
			    
			    <div class="uk-margin">
			        <div class="uk-inline">
			            <a class="uk-form-icon" href="#" uk-icon="icon: pencil"></a>
			            <input class="uk-input" type="text" name="department" placeholder="Department" >
			        </div>
			    </div>
			
			    <div class="uk-margin">
			        <div class="uk-inline">
			            <a class="uk-form-icon uk-form-icon-flip" href="#" uk-icon="icon: link"></a>
			            <input class="uk-input" type="text" name="groups" placeholder="Count groups">
			        </div>
			    </div>
			<button class="uk-button uk-button-primary">Primary</button>
		</form>
</div>


<div class="tables-university__members">


	<table class="uk-table uk-table-middle uk-table-divider">
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Age</th>
	            <th>Deleted</th>
	        </tr>
	    </thead>
	    <tbody>
	<c:forEach items="${students}" var="student">
	        <tr>
	            <td>${student.name}</td>
	            <td>${student.age}</td>
	            <td>
		            <form action="/student-app/students" method="POST">
						<input value="${student.id}" name="userId" hidden="true" >
		            	<button class="uk-button uk-button-default" type="submit">Delete</button>
		            </form>
	            </td>
	        </tr>
	        
	</c:forEach>
	</tbody>
	</table>
	
	
	
		<table class="uk-table uk-table-middle uk-table-divider">
	    <thead>
	        <tr>
	            <th>Name</th>
	            <th>Age</th>
	    	    <th>Department</th>
	    	    <th>Groups</th>
	        </tr>
	    </thead>
	    <tbody>
	<c:forEach items="${professors}" var="professor">
	        <tr>
	            <td>${professor.name}</td>
	            <td>${professor.age}</td>
	            <td>${professor.department}</td>
	            <td>${professor.groups}</td>
	        </tr>
	</c:forEach>
	</tbody>
	</table>

</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/js/uikit.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/js/uikit-icons.min.js"></script>
</body>
</html>
