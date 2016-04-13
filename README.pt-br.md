Restaurant API
=======

API REST para gerenciar um restaurante com pedidos locais e online.

## Desafio

### Casos de uso

#### Tipos de pedidos:

- Pedidos realizados no restaurante (tablet na mesa do cliente).
- Pedidos realizados online (parceiros) para entrega.

#### Sistema de cozinha

- Cozinheiro deve poder visualizar todos os pedidos realizados (no local e para entrega) por ordem de chegada.
- Cozinheiro deve poder alterar o status do pedido para indicar que o pedido está sendo preparado.
- Cozinheiro deve poder alterar o status do pedido para indicar que o pedido já está pronto.
- Garçom deve poder alterar o status do pedido para indicar que o pedido já foi entregue.

#### Sistema de mesa

- Cliente deve poder realizar o pedido, escolhendo um hamburguer e uma bebida.
- Cliente deve poder consultar o status do pedido, para verificar se o mesmo já está sendo preparado.
- Cliente deve poder consultar o valor total de seus pedidos.

#### Pedidos online (API)

- Um parceiro deve poder enviar um pedido através da API, informando os itens do pedido e o endereço de entrega.
- um parceiro deve poder consultar o status de um pedido.

## Motivação

Experimentar novas tecnologias, bibliotecas e garantir uma boa cobertura de testes.

- [RESTEasy](http://resteasy.jboss.org/) - Construir um webservice REST, a escolha foi o RESTEasy.
- [REST-assured](https://code.google.com/p/rest-assured/) - Essa biblioteca foi escolhida pela simplicidade para testar e validar serviços REST.
- [MongoDB](http://www.mongodb.org/) - Por ser um banco de dados de documento, foi escolhido para melhor representar a estrutura de um pedido (produtos, quantidades e dados da entrega) e também pela excelente performance na leitura.
- [Jongo](http://www.jongo.org/) - Essa biblioteca foi escolhida por permitir a utilização da query language do MongoDB no Java e também pela performance.

## Melhorias futuras

- **Cache**

	Talvez cache de borda com Varnish?

- **Autenticação**

	Implementar OAuth2.

- **Injeção de Dependência**

	Utilizar o Spring.

## Requisitos

- Java 7
- Jetty 9
- MongoDB 2.6.4

## Configuração

### MongoDB

1 - Faça o [download](http://www.mongodb.org/downloads) do MongoDB 2.6.4 para a versão do seus sistema operacional.

2 - Siga as recomendações de instalação e inicie o servidor

```html
$ mongod
```

## Como utilizar

Com o servidor Web e o MongoDB rodando, execute as requisições a seguir para consumir a API.

### Listar todos os produtos

**Exemplo de requisição:**

- **GET** [/products](/products)
- **Accept:** application/json
- **Content-Type:** application/json

**Exemplo de resposta:**

```json
	[
		{
			"id": "5403d655c19e51e2ea3c02c0",
			"type": "HAMBURGUER",
			"description": "X-EGG",
			"price": 10.5
		},
		{
			"id": "5403d7e6c19e51e2ea3c02c2",
			"type": "DRINK",
			"description": "Coca-Cola",
			"price": 4
		},
		{
			"id": "5403dddd9386acbf5d3f6055",
			"type": "DESSERT",
			"description": "Sorvete",
			"price": 8.5
		}
	]
```

- **200** OK

### Obter os detalhes de um produto pelo ID

**Exemplo de requisição:**

- **GET** [/products/{id}](/products/{id})
- **Accept:** application/json
- **Content-Type:** application/json

**Exemplo de resposta:**

```json
	{
		"id": "5403d655c19e51e2ea3c02c0",
		"type": "HAMBURGUER",
		"description": "X-EGG",
		"price": 10.5
	}
```

- **200** OK

### Criar um pedido local

**Exemplo de requisição:**

- **POST** [/orders](/orders)
- **Accept:** application/json
- **Content-Type:** application/json

```json
	{
		"id": "5403d7f7c19e51e2ea3c02c3",
		"table": 5,
		"total": 21.0,
		"status": "WAITING",
		"items":[  
			{  
				"product": {  
					"id": "5403d655c19e51e2ea3c02c0",
					"type": "HAMBURGUER",
					"description": "X-EGG",
					"price": 10.5
				},
				"quantity": 2
			}
		],
		"delivery": null
	}
```

**Exemplo de resposta:**

- **201** CREATED

### Criar um pedido online

**Exemplo de requisição:**

- **POST** [/orders](/orders)
- **Accept:** application/json
- **Content-Type:** application/json

```json
	{
		"id": "5203f2f7c11e54e2eb2c12e5",
		"table": null,
		"total": 21.0,
		"status": "WAITING",
		"items": [
			{
				"product": {
					"id": "5403d655c19e51e2ea3c02c0",
					"type": "HAMBURGUER",
					"price" :10.5,
					"description" :"X-EGG"
				},
				"quantity": 2
			}
		],
		"delivery": {
			"address": {
				"state": "SP",
				"number": "100",
				"street": "Rua Guararapes",
				"complement": "APTO 302",
				"city": "São Paulo",
				"zip": "04561-000"
			},
			"fullname": "Lucas Michelini Reis de Oliveira",
			"email": "lucasmro@gmail.com",
			"phone": "11986115678"
		}
	}
```

**Exemplo de resposta:**

- **201** CREATED

### Obter os detalhes de um pedido pelo ID

**Exemplo de requisição:**

- **GET** [/orders/{id}](/orders/{id})
- **Accept:** application/json
- **Content-Type:** application/json

**Exemplo de resposta:**

```json
	{
		"id": "5604d7f8c19e51e2ea3c01d5",
		"table": 5,
		"total": 4,
		"status": "WAITING",
		"items": [
			{
				"product": {
					"id": "5403d7e6c19e51e2ea3c02c2",
					"type": "DRINK",
					"price": 4,
					"description": "Coca-Cola"
				},
				"quantity": 1
			}
		],
		"delivery": null
	}
```

- **200** OK

### Listar todos os pedidos pelo número da mesa

**Exemplo de requisição:**

- **GET** [/orders/table/{table}](/orders/table/{table})
- **Accept:** application/json
- **Content-Type:** application/json

**Exemplo de resposta:**

```json
	[
		{
			"id": "5403d7f7c19e51e2ea3c02c3",
			"table": 5,
			"total": 21,
			"status": "WAITING",
			"items": [
				{
					"product": {
						"id": "5403d655c19e51e2ea3c02c0",
						"type": "HAMBURGUER",
						"price": 10.5,
						"description": "X-EGG"
					},
					"quantity": 2
				}
			],
			"delivery": null
		},
		{
			"id": "5604d7f8c19e51e2ea3c01d5",
			"table": 5,
			"total": 4,
			"status": "WAITING",
			"items": [
				{
					"product": {
						"id": "5403d7e6c19e51e2ea3c02c2",
						"type": "DRINK",
						"price": 4,
						"description": "Coca-Cola"
					},
					"quantity": 1
				}
			],
			"delivery": null
		}
	]
```

- **200** OK

### Modificar o status de um pedido pelo ID

**Exemplo de requisição:**

- **PUT** [/orders/{id}/{status}](/orders/{id}/{status})
- **Accept:** application/json
- **Content-Type:** application/json

**Exemplo de resposta:**

- **204** NO CONTENT

## Códigos de erro

| Código | Descrição   | Motivo                                       |
| ------ | ----------- | -------------------------------------------- |
| 400    | Bad Request | Parâmetros ausentes ou parâmetros inválidos. |
| 404    | Not Found   | Recurso não encontrado no banco de dados.    |
