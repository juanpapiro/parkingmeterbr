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
   * [Arquitetura](#arquitetura)
<!--te-->

Iniciar app
=================

	Iniciar banco de dados e redis com docker-compose
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
            "street":"Rua Cardeal Arcoverde",
            "number": 700,
            "neighborhood":"Pinheiros",
            "city":"São Paulo",
            "uf":"SP",
            "zipcode":"05407-001"
		}'
		
	- Consulta todos os parquímetros com paginação, caso não sejam informados page e size os valores default são 10 e 0, respectivamente
		curl --location 'http://localhost:8001/parkingmeter?page=0&size=10'
		
	- Busca parquímetro por id
		curl --location 'http://localhost:8001/parkingmeter/ed8adb42606647a3df44d65e552dfbc6'

	- Busca de endereço por cep
		curl --location 'http://localhost:8001/parkingmeter/address?cep=06826290'


Arquitetura
=================

<img src="arq_parkingmeterbr.jpg">
