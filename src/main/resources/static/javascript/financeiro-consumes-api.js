function apiFinanceiro(){
var tableTr = document.querySelectorAll('#tbody tr');
    //console.log(tableTr);  
    
    Array.prototype.forEach.call(tableTr, function(node){
        node.parentNode.removeChild(node);
    });
    

axios.get('https://apirest-financeiro.herokuapp.com/api/totalpornotafiscal')
    .then(function(response){


        for(i=0; i < response.data.length; i++){            
            //console.log(response);
            //var table = document.getElementById('table');           
            //console.log(table.rows[i].cells[0].firstChild.nodeValue);
            //console.log(table.rows[i].cells[2].firstChild.nodeValue);

            var tableElement = document.querySelector('#tbody');
                                   
            var trElement = document.createElement('tr');
            var tdCod = document.createElement('td');
            var tdEstabelecimento = document.createElement('td');
            var tdValorCompra = document.createElement('td');
            var tdAcao = document.createElement('td');
            
            var linkAcao = document.createElement('a');
            linkAcao.setAttribute('href', `/integracaoCompras/${response.data[i].compraId}`);
            var textolink = document.createTextNode('Submeter');
            linkAcao.appendChild(textolink);
            tdAcao.appendChild(linkAcao);

            tableElement.appendChild(trElement);

            trElement.appendChild(tdCod);
            trElement.appendChild(tdEstabelecimento);
            trElement.appendChild(tdValorCompra);
            trElement.appendChild(tdAcao);
          
            var compraId = document.createTextNode(response.data[i].compraId);
            tdCod.appendChild(compraId);    
    
            var estabelecimento = document.createTextNode(response.data[i].estabelecimento);
            tdEstabelecimento.appendChild(estabelecimento); 
    
            var valorCompra = document.createTextNode(response.data[i].valorTotal);
            tdValorCompra.appendChild(valorCompra);
        }


    })
    .catch(function(error){
        console.warn(error);
    });

} 
