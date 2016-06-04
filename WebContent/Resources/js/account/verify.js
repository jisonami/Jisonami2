function checkLogin(form) {
	if (form.username.value == '') {
		alert("请输入用户名!");
		form.username.focus();
		return false;
	}
	if (form.password.value == '') {
		alert("请输入登录密码!");
		form.password.focus();
		return false;
	}
	return true;
}

function checkRegister(form) {

	if (form.username.value == '') {
		alert("请输入用户名!");
		form.username.focus();
		return false;
	}
	if (form.password.value == '') {
		alert("请输入密码!");
		form.password.focus();
		return false;
	}
	if (form.verifyPassword.value == '') {
		alert("请输入确认密码!");
		form.verifyPassword.focus();
		return false;
	}
	if (form.password.value != form.verifyPassword.value) {
		alert("两次输入密码不一致！");
		form.password.focus();
		return false;
	}
	return true;
}