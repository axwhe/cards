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
	<div class="well lead">Bank Detail Registration Form</div>
 	<form:form method="POST" modelAttribute="bankdetail" class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>
		
		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="bankName">Bank Name</label>
				<div class="col-md-7">
					<form:input type="text" path="bankName" id="bankName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="bankName" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="cardNumber">Card Number</label>
				<div class="col-md-7">
					<form:input type="text" path="cardNumber" id="cardNumber" class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="cardNumber" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-lable" for="expiryDate">Expiry Date</label>
				<div class="col-md-7">
						<form:input type="text" path="expiryDate" id="expiryDate" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="expiryDate" class="help-inline"/>
						</div>
				</div>
			</div>
		</div>



		<div class="row">
			<div class="form-actions floatRight">
				<c:choose>
					<c:when test="${edit}">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
					</c:when>
					<c:otherwise>
						<input type="submit" value="add" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</div>
</body>
</html>