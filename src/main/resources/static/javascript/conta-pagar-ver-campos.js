$("#statusConta").change(function(){
	if($(this).val() == "PAGO"){
	
		$('#dtPago').show();
		$('#dtPagamento').attr('required', 'required');
		$('#dtPagamento').attr('data-error', 'Informe a data de pagamento.');
		$('#vlPago').show();
		$('#valorPago').attr('required', 'required');		
		$('#valorPago').attr('data-error', 'Informe o valor pago.');
		
	} else {
		
		$('#dtPago').hide();
		$('#dtPagamento').removeAttr('required');
		$('#dtPagamento').removeAttr('data-error');
		$('#vlPago').hide();
		$('#valorPago').removeAttr('required');
		$('#valorPago').removeAttr('data-error');
	}
});

$("#statusConta").trigger("change");


$('#infoClick').after(function () {
	//event.preventDefault();
	var msg = $('#message');
	var msgErro = $('#messageErro');
	
	if(msg.length == 1){
		//console.log(msg[0].innerText);
		//Materialize.toast(msg, 2000);
		iziToast.show({
	        color: 'dark',
	        icon: 'fa fa-user',
	        title: '',
	        message: msg[0].innerText,
	        position: 'topRight', // center, bottomRight, bottomLeft, topRight, topLeft, topCenter, bottomCenter
	        progressBarColor: 'rgb(0, 255, 184)'      

		});	
	}
	
	if(msgErro.length == 1){
		iziToast.error({
			title: 'Erro',
			message: msgErro[0].innerText
		});
	}

});

$(document).ready(function(){
	$('#copyText').on("click", function(){
		var copyText = $('#codBarras');
		
		copyText.select();
		//copyText.setSelectionRange(0, 99999); /*For mobile devices*/
		
		document.execCommand("copy");
		alert("Código copiado!");
	});
});