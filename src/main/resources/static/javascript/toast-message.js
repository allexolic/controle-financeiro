$("#btnClick").after(function() {
	var msgAdd = $('#messageAdd');
	var msgEdit = $('#messageEdit');
	
	if(msgAdd.length == 1){
		iziToast.show({
			color: 'dark',
			icon: 'fa fa-user',
			title: '',
			message: msgAdd[0].innerText,
			position: 'topRight',
			progressBarColor: 'rgb(0, 255, 184)'
		});
	}
	
	if(msgEdit.length == 1){
		iziToast.show({
			color: 'dark',
			icon: 'fa fa-user',
			title: '',
			message: msgEdit[0].innerText,
			position: 'topRight',
			progressBarColor: 'rgb(0, 255, 184)'
		});
	}
});