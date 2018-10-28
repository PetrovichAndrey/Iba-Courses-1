<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<html>
<body>
	<form action="/student-app/students" method="POST">
		<input type="text" name="id"> <input type="text" name="firstName"> <input type="text" name="secondName"> <input type="text" name="avgMark"> <input type="text" name="groupNumber"> 
		<input type="submit"> <input type="hidden" name="action" value="Add">
	</form>

	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>firstName</th>
				<th>secondName</th>
				<th>avgMark</th>
				<th>groupNumber</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${students}">
			
			<tr>
				<td>${student.id}</td>
				<td>${student.firstName}</td>
				<td>${student.secondName}</td>
				<td>${student.avgMark}</td>
				<td>${student.groupNumber}</td>
				<td> <form action="/student-app/students" method="POST"> <input type="submit" name="action" value="${student.id}"> </form> </td>
			</tr>		
			</c:forEach>
			
		</tbody>

	</table>
</body>
</html>
