<%@page import="com.delivery"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delivery Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Components/delivery.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-7">
				<h1 class="m-3">Delivery Management</h1>

				<form id="formDelivery" name="formDelivery" method="post"
					action="delivery.jsp">

					Delivery Name: <input id="deliveryName" name="deliveryName"
						type="text" class="form-control form-control-sm"> <br>
					Address: <input id="deliveryAddress" name="deliveryAddress"
						type="text" class="form-control form-control-sm"> <br>
					Contact: <input id="deliveryContact" name="deliveryContact"
						type="text" class="form-control form-control-sm"> <br>
					<div class="form-group row">
						<label for="example-date-input" class="col-2 col-form-label">Date</label>
						<div class="col-10">
							<input class="form-control" type="date" value="2020-10-20"
								id="deliveryDate" name="deliveryDate">
						</div>
					</div>
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidDeliveryIDSave" name="hidDeliveryIDSave" value="">

				</form>
<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

			</div>
		</div>




		<br>
		<div id="divDeliveryGrid">

			<%
			delivery deliveryObj = new delivery();
			out.print(deliveryObj.readDelivery());
			%>
		</div>
</body>
</html>