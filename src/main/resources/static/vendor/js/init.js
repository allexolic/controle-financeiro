(function($){
  $(function(){

    $('.button-collapse').sideNav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

//select

$(document).ready(function(){
	$('select').material_select();
});

$(document).ready(function() {
    Materialize.updateTextFields();
  });

//inicializa modal com JQuery
$(document).ready(function(){
	$('.modal').modal();
});

//inicializa modal
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    //var instances = M.Modal.init(elems, options);
  });

$(document).ready(function () {
	$('.tooltipped').tooltip({delay: 50});
});

//format moeda
$(function () {
	$('.js-moeda').maskMoney({
		decimal : ',',
		thousands: '.',
		allowZero : true
	});
});

//format numero
$(function (){
	$('.js-numero').maskMoney({
		thousands : '.',
		allowZero : true
	});
});

$('.datepicker').pickadate({
    monthsFull: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabádo'],
    weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
    today: 'Hoje',
    clear: 'Limpar',
    close: 'Pronto',
    labelMonthNext: 'Próximo mês',
    labelMonthPrev: 'Mês anterior',
    labelMonthSelect: 'Selecione um mês',
    labelYearSelect: 'Selecione um ano',
    selectMonths: true, 
    selectYears: 15,
    format: 'dd/mm/yyyy'
 });

//Toast


