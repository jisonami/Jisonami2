window.onload = function(){
	var blogTypes = window.document.getElementsByName("blogType");
	for (var i = 0; i < blogTypes.length; i++) {
		blogTypes[i].onclick = function(){
			var blogTypeIds = window.document.getElementById("blogTypeIds");
			var blogTypes = window.document.getElementById("blogTypes");
			var blogTypeId = this.getAttribute("id");
			var blogTypeName = document.getElementById("blogTypeName"+this.getAttribute("id")).textContent;
			if(this.checked){
				if(blogTypeIds.value==""){
					blogTypeIds.value = blogTypeId;
					blogTypes.value = blogTypeName;
				} else {
					blogTypeIds.value = blogTypeIds.value + "," + blogTypeId;
					blogTypes.value = blogTypes.value + "," + blogTypeName;
				}
			}else{
				blogTypeIds.value = blogTypeIds.value.replace(","+blogTypeId,"");
				blogTypeIds.value = blogTypeIds.value.replace(blogTypeId+",","");
				blogTypeIds.value = blogTypeIds.value.replace(blogTypeId,"");
				blogTypes.value=blogTypes.value.replace(","+blogTypeName,"");
				blogTypes.value=blogTypes.value.replace(blogTypeName+",","");
				blogTypes.value=blogTypes.value.replace(blogTypeName,"");
			}
		};
	}
};

