

//$(document).ready(function(){
//	if($("#seeAll").length>0)
//		$("#seeAll").on("click", function(){
//			$.ajax({
//				url: $("#root").val()+"/seeAll",
//				type: "POST",
//				success: function(data){
//					showUsuarios(data);
//				},
//				error: function(event){
//					
//				} 
//			});
//		});
//});
//
////function showUsuarios(data){
////	$("#userForm").empty();
////	for(var i=0; i<data.length; i++){
////		if(data[i].userName!="Administrador"){
////			
////			var urlBan = $("#root").val() + "/ban/" + data[i].idUsuario;
////			
////			var spanActiveOrNot = $("<span/>").html(data[i].enabled?" Activo":" Inactivo").addClass("nota " + data[i].enabled?"green":"red") 
////			var span = $("<span/>").attr("id", "user" + data[i].idUsuario);
////			var button = $("<input/>").attr("type","button").attr("id","banUser"+data[i].idUsuario).val(data[i].enabled?"Banear":"Activar");
////			
////			$(button).on("click",{url:urlBan},ban);
////			
////			$(span).append(data[i].userName);
////			$(span).append(spanActiveOrNot);
////			$(span).append(button);
////			$("#userForm").append(span);
////			$("#userForm").append("<br/>");
////		}
////	}
////}
//
//function ban(event){
//	$.ajax({
//		url: event.data.url,
//		type: "POST",
//		success: function(data){
//			location.reload();
//		},
//		error: function(event){
//			console.log(event);
//		} 
//	});
//	
//}

