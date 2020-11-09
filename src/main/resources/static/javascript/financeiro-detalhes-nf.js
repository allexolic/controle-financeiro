function apiFinanceiroDetalhes(){
var tableTr = document.querySelectorAll('#tbody tr');
    //console.log(tableTr);  
var id =  document.getElementById('codigo').value;
 //console.log(id);    
 
    Array.prototype.forEach.call(tableTr, function(node){
        node.parentNode.removeChild(node);
    });
    

axios.get(`https://apirest-financeiro.herokuapp.com/api/notafiscal/${id}`)
    .then(function(response){

    
        for(i=0; i<response.data.compraItem.length; i++){            
            console.log(response.data.compraItem[i].id);
            //var table = document.getElementById('table');           
            //console.log(table.rows[i].cells[0].firstChild.nodeValue);            
            var tableElement = document.querySelector('#tbody');
                                   
            var trElement = document.createElement('tr');
            var tdCod = document.createElement('td');
            var tdProduto = document.createElement('td');
            var tdValorProduto = document.createElement('td');
            var tdValorDesconto = document.createElement('td');
            //var tdAcao = document.createElement('td');            
            
            //var linkAcao = document.createElement('a');
            //linkAcao.setAttribute('href', '#');
            //var textolink = document.createTextNode('Editar');
            //linkAcao.appendChild(textolink);
            //tdAcao.appendChild(linkAcao);

            tableElement.appendChild(trElement);

            trElement.appendChild(tdCod);
            trElement.appendChild(tdProduto);
            trElement.appendChild(tdValorProduto);
            trElement.appendChild(tdValorDesconto);
            //trElement.appendChild(tdAcao);                       
           
            //trElement.setAttribute('th:each', 'compraItens : ${compraItem}');
            //tdCod.setAttribute('th:text', '${compraItens.codigo}');
            var idNota = document.createTextNode(response.data.compraItem[i].id);
            tdCod.appendChild(idNota);    
            
            //tdProduto.setAttribute('th:value', '${compraItens.produto}');
            var produto = document.createTextNode(response.data.compraItem[i].produto);
            tdProduto.appendChild(produto); 

            //tdValorProduto.setAttribute('th:text', '${compraItens.valorProduto}');
            var valorProduto = document.createTextNode(response.data.compraItem[i].valorProduto);
            tdValorProduto.appendChild(valorProduto);
            
            //tdValorDesconto.setAttribute('th:text', '${compraItens.valorDesconto}');
            var valorDesconto = document.createTextNode(response.data.compraItem[i].valorDesconto);
            tdValorDesconto.appendChild(valorDesconto);                 
            
        }
        
        $(document).ready(function(){
            $('#tbody').pageMe({
              pagerSelector:'#myPager',
              activeColor: 'black',
              prevText:'Prev',
              nextText:'Next',
              showPrevNext:true,
              hidePageNumbers:false,
              perPage:5
            });
    				
         });

    })
    .catch(function(error){
        console.warn(error);
    });

} 

apiFinanceiroDetalhes();



