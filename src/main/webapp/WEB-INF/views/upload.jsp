<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bank Detail Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

 	<div class="generic-container">
	<div class="well lead">Bank Detail Upload</div>
		<form:form method="POST" modelAttribute="bankDetailFile" enctype="multipart/form-data" class="form-horizontal">
			
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="file">Upload a CSV file</label>
				<div class="col-md-7">
					<form:input type="file" path="file" id="file" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="file" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="file">file Type</label>
				<div class="col-md-7">
					<form:input type="text" path="fileType" id="fileType" class="form-control input-sm" value="CSV"></form:input>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-actions floatRight">
				<input type="submit" value="Upload" class="btn btn-primary btn-sm">
			</div>
		</div>

		</form:form>
	</div>
</body>
</html>