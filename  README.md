Por  tratar-se de um framework com o qual tenho maior familiridade, a api foi desenvolvida utilizando o framework Spring utilizando a linguagem Java.
Executar: mvn clean install.
Empacotar: war, jar.
Requisitos: 
Endpoint cadastrar produto: localhost:8080/api/produto
Endpoint adicionar produto ao carrinho: localhost:8080/api/addcar/3
Endpoint remover produto do carrinho: localhost:8080/api/removecar/1
Endpoint listar produtos (ordenao opcional): localhost:8080/api/produtos?orderByPreco=asc
Os valores de frete, total e sub-total sao calculados dinamicamente.
Testes:  Para falicitar os testes, foi utilizado o swagger, apos executar a aplicação, acesse: http://localhost:8080/swagger-ui.html, onde estao disponiveis todos os endpoint da aplicaçao. Em virtude do swagger não aceitar cookies, alguns testes foram realizados utilizando o postman. Quando o produto e adicionado ao carrinho em segundo plano eles sao aramazenados em um cookie que fica armazenado no cliente. Portanto recomenda-se, utilizar o postman para tesntar o endpoint de adicionar produtos ao carrinho.
