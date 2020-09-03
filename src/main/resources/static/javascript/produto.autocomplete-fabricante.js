var Contas = Contas || {};

Contas.Autocomplete = (function (){
	function Autocomplete (){
		
	}
	
	Autocomplete.prototype.iniciar = function (){
		$.ajax({
			type: 'GET',
			url: '/fabricantes/filtro?nome=',
			success: function (response){
				var fabricantesServidor = response;
				var fabricantesSugestao = {};

				
				for (var i = 0; i < fabricantesServidor.length; i++){
					fabricantesSugestao[fabricantesServidor[i].nome] = fabricantesServidor[i].flag;

				}
				
				$('input.autocomplete').autocomplete({
					data: fabricantesSugestao,
					limit: 6,
					onAutocomplete: function(texto){
						onSendItem(texto);
						
					},
					
					minLength: 2
				});
				
				function onSendItem(texto){
					var codigo = 0;
					
					fabricantesServidor.filter(function(obj){
						if(obj.nome === texto){
							codigo = obj.codigo;
						}
					});
					
					if(codigo > 0){
						$('#fabricante').attr('value', codigo);
					}
				}
			}
		});
	};
	
	return Autocomplete;
}());

$(function (){
	var autocomplete = new Contas.Autocomplete();
	autocomplete.iniciar();
});