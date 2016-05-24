window.onload = function(){
	var blogTypes = window.document.getElementsByName("blogType");
	for (var i = 0; i < blogTypes.length; i++) {
		blogTypes[i].onclick = function(){
			var blogTypeIds = window.document.getElementById("blogTypeIds");
			var blogTypes = window.document.getElementById("blogTypes");
			if(this.checked){
				if(blogTypeIds.value==""){
					blogTypeIds.value = this.getAttribute("blogTypeId");
					blogTypes.value = this.getAttribute("blogTypeName");
				} else {
					blogTypeIds.value = blogTypeIds.value + "," + this.getAttribute("blogTypeId");
					blogTypes.value = blogTypes.value + "," + this.getAttribute("blogTypeName");
				}
			}else{
				blogTypeIds.value = blogTypeIds.value.replace(","+this.getAttribute("blogTypeId"),"");
				blogTypeIds.value = blogTypeIds.value.replace(this.getAttribute("blogTypeId")+",","");
				blogTypeIds.value = blogTypeIds.value.replace(this.getAttribute("blogTypeId"),"");
				blogTypes.value=blogTypes.value.replace(","+this.getAttribute("blogTypeName"),"");
				blogTypes.value=blogTypes.value.replace(this.getAttribute("blogTypeName")+",","");
				blogTypes.value=blogTypes.value.replace(this.getAttribute("blogTypeName"),"");
			}
		};
	}
};

