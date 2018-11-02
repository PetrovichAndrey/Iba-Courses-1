<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/css/uikit.min.css" />
<title>Requests</title>
</head>
<body>
<style>
.students-changes-desk {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: baseline;
    width: 100%;
}
.students-changes-desk>div{
width: 38%;
margin-left: 1%;
}

.students>tbody>tr>td:first-child{
cursor:pointer;
}
.students-changes-desk>form{
width: 30%;
margin-left: 1%;
display:flex;
flex-direction: column;
justify-content: center;
align-items: center;
}
</style>
<h1 class="uk-heading-divider">Student Cabinet</h1>
<div class="students-changes-desk">
	<form class="addStudentForm" action="/student-app/page" method="post">
	<h1 class="uk-heading-line uk-text-center"><span>Add student</span></h1>
		<div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: user"></span>
	            <input class="uk-input name" required="required" placeholder="Name" name="firstName" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: users"></span>
	            <input class="uk-input surname" required="required" placeholder="Surname" name="secondName" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: google"></span>
	            <input class="uk-input group" required="required" placeholder="Group number" name="groupNumber" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: pencil"></span>
	            <input class="uk-input mark" required="required" placeholder="Average mark" name="avgMark" type="text">
	        </div>
	    </div>
	
		<button class="uk-button uk-button-primary addStudentBtn">Add student</button>
	</form>
	
	<div>
	<h1 class="uk-heading-line uk-text-center"><span>Student list</span></h1>
		<table class="uk-table uk-table-striped students">
			<thead>
			
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Group</th>
					<th>Average mark</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
			
				
			
			</tbody>
		
		
		
		</table>
	</div>
	
	<form class="updateStudentForm">
	<h1 class="uk-heading-line uk-text-center"><span>Update student</span></h1>
	
		<div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: user"></span>
	            <input class="uk-input update name" required="required" placeholder="Name" name="firstName" type="text">
	            <input hidden class="update id" value="" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: users"></span>
	            <input class="uk-input update surname" required="required" placeholder="Surname" name="secondName" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: google"></span>
	            <input class="uk-input update group" required="required" placeholder="Group number" name="groupNumber" type="text">
	        </div>
	    </div>
	    <div class="uk-margin">
	        <div class="uk-inline">
	            <span class="uk-form-icon" uk-icon="icon: pencil"></span>
	            <input class="uk-input update mark" required="required" placeholder="Average mark" name="avgMark" type="text">
	        </div>
	    </div>
	
		<button class="uk-button uk-button-primary updateStudentBtn">Update student</button>
	</form>
</div>

<div class="student-by-name">
	<div class="uk-margin">
            <input class="uk-input name-input" type="text" placeholder="Student name">
	</div>

	<button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom searcher">Search</button>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript"></script>
<!-- UIkit JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/js/uikit.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.20/js/uikit-icons.min.js"></script>
<script type="text/javascript">
	
	function loadStudents() {
		var tableLoad = "";
		$.ajax({
			url:"/student-app/students",
			type:'GET',
			success: function (data) {
				$.each(data, function(key,value){
					tableLoad += "<tr>"+
						"<td class=\"nameColumn\" data-id=\""+value.id+"\">"+value.firstName+"</td>"+
						"<td>"+value.secondName+"</td>"+
						"<td>"+value.groupNumber+"</td>"+
						"<td>"+value.avgMark+"</td>"+
						"<td><button class=\"buttonDelete uk-button uk-button-default\" user=\""+value.id+"\">Delete</button></td>"+
					"</tr>";
				});
				$('.students>tbody').html(tableLoad);
			}
		});
	}
	
	loadStudents();
	
	
	$(document).on("click",".buttonDelete",function(event){
		$.ajax({
			url:"/student-app/students/" + $(this).attr('user'),
			type:'DELETE',			
			success: function (data) {
				loadStudents();
			}
		});		
	});
	
	
	$(document).on("click",".addStudentBtn",function(event){
		
		var name =$('.name').val();
		var surname = $('.surname').val();
		var group= $('.group').val();
		var avgMark = $('.mark').val();
		
		$.ajax({
			url:"/student-app/students",
			type:'POST',
			contentType: "application/json; charset=utf-8",
		    dataType: "json",
			data: JSON.stringify({
				firstName:name,
				secondName:surname,
				groupNumber:group,
				avgMark:avgMark
			}),
			success: function(data){
				loadStudents();	
				$(".addStudentForm>div>div>input").each(function(key,value){
					$(value).val("");
				});
			}
		});
		
		event.preventDefault();
	});
	
	
	$(document).on("click",".students>tbody>tr>td:first-child", function(){
		$.ajax({
			url:"/student-app/students/"+$(this).attr("data-id"),
			type:"GET",
			dataType:"json",
			success: function(data){
				$(".update.id").val(data.id);
				$(".update.name").val(data.firstName);
				$(".update.surname").val(data.secondName);
				$(".update.group").val(data.groupNumber);
				$(".update.mark").val(data.avgMark);
			}
		});
	});

	$(document).on("click",".updateStudentBtn",function(event){
	$.ajax({
		url:"/student-app/students",
		type:'PUT',
		contentType: "application/json; charset=utf-8",
	    dataType: "json",
		data: JSON.stringify({
			id:$(".update.id").val(),
			firstName:$(".update.name").val(),
			secondName:$(".update.surname").val(),
			groupNumber:$(".update.group").val(),
			avgMark:$(".update.mark").val()
		}),
		success: function(data){
			loadStudents();	
			$(".updateStudentForm>div>div>input").each(function(key,value){
				$(value).val("");
			});
		}
	});
	
	event.preventDefault();
});
	
	$(document).on("click", ".searcher",function(){
		var tableLoad = "";
		$.ajax({
			url:"/student-app/students/?name="+$(".name-input").val(),
			type:"GET",
			success: function(data){
				$.each(data, function(key,value){
					tableLoad += "<tr>"+
						"<td class=\"nameColumn\" data-id=\""+value.id+"\">"+value.firstName+"</td>"+
						"<td>"+value.secondName+"</td>"+
						"<td>"+value.groupNumber+"</td>"+
						"<td>"+value.avgMark+"</td>"+
						"<td><button class=\"buttonDelete uk-button uk-button-default\" user=\""+value.id+"\">Delete</button></td>"+
					"</tr>";
				});
				$('.students>tbody').html(tableLoad);
				
				
				$(".name-input").val("");
			}			
		});
	});c
</script>
</body>
</html>