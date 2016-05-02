
	$(document).ready(function() {

		console.log("Here we go!");
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		$("#container").css("height", $(window).height()-50);
		if($("#container #login").length>0 || $("#container #register").length>0) 
			$("#container").css("background-image","url('./resources/images/vynil.jpg')");
		
		if($("#container #search").length>0){
			var angle = 0;
			setInterval(function(){
				angle+=3;
				$("#logo").rotate(angle);
			},50);	
		}
		
		$("#searchForm").submit(function(event){
			var search = { "searched": this[0].value };
			$.ajax({
				url: $("#searchForm").attr("action"),
				data: JSON.stringify(search),
				type: "POST",
		        beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        },
				success: function(data){
					hideErrorMessage("searchForm");					
					if(data!=""){
						$("#searched").html(data.searched);	
						var noresults = "Sin resultados";
						showAlbumes(data,noresults);
						showArtistas(data,noresults);
						showSellos(data,noresults);
					}
				}, 
				error: function(event){
					if(event.responseJSON.fatalError){
						$("#errorContainer").css("visibility","visible");
						$("#errorContainer span").html(event.responseJSON.message);	
					}else
						showErrorMessage("searchForm", event.responseJSON.fieldErrors);
				}
			});
			
			event.preventDefault();
		});
		
	});
		
	function showErrorMessage(formId, fieldErrors){
		if(fieldErrors.search!=null){
			if($("#"+formId+" .errorInForm").length==0)
				$("#"+formId).prepend($("<span/>").html(fieldErrors.search).addClass("errorInForm"));
		}
	}
	
	function hideErrorMessage(formId){
		if($("#"+formId+" .errorInForm").length>0){
			var errors = $("#"+formId+" .errorInForm");
			for(var i=0; i<errors.length; i++)
				$(errors[0]).remove();
		}
	}
	
	function showAlbumes(data,noresults){
		$("#albumesContent").empty();
		if(data.albumes.length>0){
			for(var i=0; i<data.albumes.length; i++){
				var art = "Varios";
				if(data.albumes[i].artista.length==1)
					art = data.albumes[i].artista[0].nombre
				
				var span = $("<span/>").attr("id","album"+i).html(
								art+" - "+data.albumes[i].nombre + ((data.albumes[i].anyo_edicion!=null)?" ("+data.albumes[i].anyo_edicion+")":"")
							);
				$("#albumesContent").append(span);
				$("#albumesContent").append("<br/>");
			}
		}else{
			var span = $("<span/>").html(noresults); 
			$("#albumesContent").append(span);
		}
	}
	
	function showArtistas(data,noresults){
		$("#artistasContent").empty();
		if(data.artistas.length>0){
			for(var i=0; i<data.artistas.length; i++){
				var span = $("<span/>").attr("id","artista"+i).html(data.artistas[i].nombre); 
				$("#artistasContent").append(span);
				$("#artistasContent").append("<br/>");
			}
		}else{
			var span = $("<span/>").html(noresults); 
			$("#artistasContent").append(span);
		}		
	}
	
	function showSellos(data,noresults){
		$("#sellosContent").empty();
		if(data.sellos.length>0){
			for(var i=0; i<data.sellos.length; i++){
				var span = $("<span/>").attr("id","sello"+i).html(data.sellos[i].sello); 
				$("#sellosContent").append(span);
				$("#sellosContent").append("<br/>");
			}						
		}else{
			var span = $("<span/>").html(noresults); 
			$("#sellosContent").append(span);
		}
	}
	