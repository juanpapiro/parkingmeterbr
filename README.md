<h1 align="center">ParkingmeterBR</h1>

<p align="center">Projeto responsável por cadastro e consulta de parquímetros</p>

Sobre
=================

	Projeto para cadastrar e consultar parquímetros, uso de base de dados Postgresql e cache para limitar acesso à base de dados desnecessariamente;




Tabela de conteúdo
=================
<!--ts-->
   * [Sobre](#Sobre)
   * [Tabela de Conteúdo](#tabela-de-conteúdo)
   * [Iniciar App](#iniciar-app)
   * [Utilização/documentação](#utilização-documentação)
   * [Testes](#testes)
   * [Arquitetura](#arquitetura)
<!--te-->

Iniciar app
=================

	Iniciar banco de dados com docker-compose
		na raiz do projeto
		- executar docker-compose -f dc-pbr.yml up -d	

	Iniciar aplicação
		- executar mvn spring-boot:run no diretório da aplicação (java 17)
		- ou startar pela IDE


Utilização/documentação
=================

    - Swagger acessível em http://localhost:8001/swagger-ui.html

	- Após subir a aplicação, pode ser cadastrado um parquímetro com exemplo de curl abaixo:
        curl --location 'http://localhost:8001/parkingmeter' \
        --header 'Content-Type: application/json' \
        --data '{
        "street":"Rua Leblon",
        "number": 5,
        "neighborhood":"Jardim São Vicente",
        "city":"Embu",
        "uf":"SP",
        "zipcode":"06826-270"
        }'
		
	- Consulta todos os parquímetros com paginação, caso não sejam informados page e size os valores default são 10 e 0, respectivamente
		curl --location 'http://localhost:8001/parkingmeter?size=1&page=1'
		
	- Busca parquímetro por id
		curl --location 'http://localhost:8001/parkingmeter/f679af614f1810e505df08eac609c16c' \
        --data ''
	
Testes
=================
	Não foram implementados testes unitários
	

Arquitetura
=================

<img src="arq_mongoteste.jpg">
