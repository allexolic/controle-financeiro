var Produtos = Produtos || {};

Produtos.Autocomplete = (function (){
	function Autocomplete (){
		
	}
	
	Autocomplete.prototype.iniciar = function (){
		$.ajax({
			type: 'GET',
			url: '/produtos/filtro?nomeProduto=',
			success: function (response){
				var produtosServidor = response;
				var produtosSugestao = {};

				for (var i = 0; i < produtosServidor.length; i++){
					produtosSugestao[produtosServidor[i].nomeProduto] = produtosServidor[i].flag;

				}
				
				$('input.autocomplete').autocomplete({
					data: produtosSugestao,
					limit: 6,
					onAutocomplete: function(texto){
						onSendItem(texto);
						
					},
					minLength: 2
				
				});
				
				function onSendItem(texto){
					var codigo = 0;
					
					produtosServidor.filter(function(obj){
						if(obj.nomeProduto == texto){
							codigo = obj.codigo;
							
						}
					});
					if(codigo > 0){
						$('#produto').attr('value', codigo);
					}
				}
			}
		});
	};
	return Autocomplete;
}());

$(function (){
	var autocomplete = new Produtos.Autocomplete();
	autocomplete.iniciar();
});