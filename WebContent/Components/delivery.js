$(document).ready(function(){
	$("#alertSuccess").hide();
	$("#alertError").hide();
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateDeliveryForm();
	if(status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
	
// If valid------------------------
	
	//$("#formDelivery").submit
	var type = ($("#hidDeliveryIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "deliveryAPI",
		type : type,
		data : $("#formDelivery").serialize(),
		dataType : "text",
		complete : function(response, status) 
		{
			onDeliverySaveCompelet(response.responseText, status);
		}
	});
});
	
	function onDeliverySaveCompelet(response, status) {
		if (status == "success") 
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success") 
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				$("#divDeliveryGrid").html(resultSet.data);
				
			} else if (resultSet.status.trim() == "error") {
				
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
		}
		$("#hidDeliveryIDSave").val("");
		$("#formDelivery")[0].reset();
	}
	



//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
		{
			$("#hidDeliveryIDSave").val($(this).closest("tr").find('#hidDeliveryIDUpdate').val());
			$("#deliveryName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#deliveryAddress").val($(this).closest("tr").find('td:eq(1)').text());
			$("#deliveryContact").val($(this).closest("tr").find('td:eq(2)').text());
			$("#deliveryDate").val($(this).closest("tr").find('td:eq(3)').text());
		});


//remove
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "deliveryAPI",
		type : "DELETE",
		data : "deliveryId=" + $(this).data("deliveryid"),
		dataType : "text",
		complete : function(response, status) 
		{
			onDeliveryDeleteComplete(response.responseText, status);
		}
	});
});


function onDeliveryDeleteComplete(response, status) {
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			
			$("#divDeliveryGrid").html(resultSet.data);
		
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//CLIENTMODEL=========================================================================
function validateDeliveryForm() {

	if ($("#deliveryName").val().trim() == "") {
		return "Insert Delivery Name.";
	}

	if ($("#deliveryAddress").val().trim() == "") {
		return "Insert Delivery Address.";
	}

	if ($("#deliveryContact").val().trim() == "") {
		return "Insert contact.";
	}

	if ($("#deliveryDate").val().trim() == "") {
		return "Insert date.";
	}


	
	return true;
}


