var Contas = Contas || {};

Contas.DialogRemover = (function (){
	
	function DialogRemover(){
		this.modal = $('#modal-remover-registro');
		
		this.botaoRemover = $('.modal-trigger');
		//this.alertInfo = $('#info');
		//this.alertErro = $('#erro');
	}
	
	DialogRemover.prototype.iniciar = function (){
		this.botaoRemover.on('click', onModalShow.bind(this));
		
	};
	
	function onModalShow(evento){
		evento.preventDefault();
		var botao = $(evento.currentTarget);
		var codigo = botao.data('codigo');
		var nome = botao.data('nome');
		
		var form = this.modal.find('form');
		var action = form.data('url-remover');
		if(!action.endsWith('/')){
			action += '/';
		}
		
		form.attr('action', action + codigo);
		
		//console.log('action', action + codigo);
		
		this.modal.find('.modal-content span').html('Confirma a exclusão de <strong> ' + nome + '</strong>');
	}
	
	
	return DialogRemover;
}());

$(function (){
	var removerFabricante = new Contas.DialogRemover();
	removerFabricante.iniciar();
});


