create schema mercado2015;
-- drop database mercado2015;

use mercado2015;

create table mercadoria(
	id integer unsigned not null auto_increment,
    descricao varchar(100) not null,
    preco decimal(10,2) not null,
    constraint pk_compra_id primary key(id)
);

create table compra(
	id integer unsigned not null auto_increment,
    data varchar(10) not null,
    constraint pk_compra_id primary key(id)
);

create table itemcompra(
	id integer unsigned not null auto_increment,
    idmercadoria integer unsigned not null,
    idcompra integer unsigned not null,
    quantidade integer unsigned not null,
    constraint pk_itemcompra_id primary key(id),
    constraint f_itemcompra_cliente foreign key(idmercadoria)
		references mercadoria(id),
	constraint f_itemcompra_compra foreign key(idcompra)
		references compra(id)
);

create table pagamento(
	id integer unsigned not null auto_increment,
    data_pagamento varchar(10) not null,
    valor decimal(10,2),
    id_compra integer unsigned not null,
    constraint pk_pagamento_id primary key(id),
    constraint f_pagamento_compra foreign key(id_compra)
		references compra(id)
);

/*
insert into mercadoria (id,descricao,preco) values (1,'Sabão em Pó - 1kg', 8.70);
insert into mercadoria (id,descricao,preco) values (2,'Amaciante - 500ml',12.10);
insert into mercadoria (id,descricao,preco) values (3,'Detergente - 500ml', 3.20);
insert into mercadoria (id,descricao,preco) values (4,'Água Sanitária - 5L', 9.94);
insert into mercadoria (id,descricao,preco) values (5,'Esponja de Aço', 1.20);
insert into mercadoria (id,descricao,preco) values (6,'Buchinha de Pia', 1.13);
insert into mercadoria (id,descricao,preco) values (7,'Sabão em Pedra', 2.00);
insert into mercadoria (id,descricao,preco) values (8,'Sabonete', 1.50);
insert into mercadoria (id,descricao,preco) values (9,'Shampoo - 250ml', 13.45);
insert into mercadoria (id,descricao,preco) values (10,'Condicionador - 250ml', 14.13);
insert into mercadoria (id,descricao,preco) values (11,'Desinfetante - 1L',3.50);
insert into mercadoria (id,descricao,preco) values (12,'Lustra Móveis - 150ml', 15.89);
insert into mercadoria (id,descricao,preco) values (13,'Tira Manchas - 150ml', 6.80);
insert into mercadoria (id,descricao,preco) values (14,'Limpa Vidros - 150ml', 8.90);
insert into mercadoria (id,descricao,preco) values (15,'Álcool - 1L', 4.50);
insert into mercadoria (id,descricao,preco) values (16,'Saco de Lixo - 30l', 8.97);
insert into mercadoria (id,descricao,preco) values (17,'Saco de Lixo - 50l', 12.34);
insert into mercadoria (id,descricao,preco) values (18,'Refrigerante - 2l', 4.5);
insert into mercadoria (id,descricao,preco) values (19,'Suco garrafa - 1l', 5.67);
insert into mercadoria (id,descricao,preco) values (20,'Suco caixinha - 500ml', 2.34);
insert into mercadoria (id,descricao,preco) values (21,'Suco sachê', 0.89);
insert into mercadoria (id,descricao,preco) values (22,'Leite integral - 1L', 3.89);
insert into mercadoria (id,descricao,preco) values (23,'Leite desnatado - 1L',3.89);
insert into mercadoria (id,descricao,preco) values (24,'Arroz - 5kg',18.45);
insert into mercadoria (id,descricao,preco) values (25,'Feijão - 2kg', 19.40);
insert into mercadoria (id,descricao,preco) values (26,'Macarrão - 500g', 8.70);
insert into mercadoria (id,descricao,preco) values (27,'Extrato de Tomate - 350g', 12.10);
insert into mercadoria (id,descricao,preco) values (28,'Molho de Tomate - 350g', 3.20);
insert into mercadoria (id,descricao,preco) values (29,'Sal - 500g', 9.94);
insert into mercadoria (id,descricao,preco) values (30,'Açucar - 1kg', 1.20);
insert into mercadoria (id,descricao,preco) values (31,'Achocolatado - 500g', 1.13);
insert into mercadoria (id,descricao,preco) values (32,'Bolacha - 200g', 2.00);
insert into mercadoria (id,descricao,preco) values (33,'Café - 500g', 1.50);
insert into mercadoria (id,descricao,preco) values (34,'Farofa Pronta - 500g', 13.45);
insert into mercadoria (id,descricao,preco) values (35,'Fubá - 500g', 14.13);
insert into mercadoria (id,descricao,preco) values (36,'Farinha de Trigo - 1kg', 3.50);
insert into mercadoria (id,descricao,preco) values (37,'Farinha de Milho - 500g', 15.89);
insert into mercadoria (id,descricao,preco) values (38,'Farinha de Mandioca - 500g', 6.80);
insert into mercadoria (id,descricao,preco) values (39,'Sardinha - 250g', 8.90);
insert into mercadoria (id,descricao,preco) values (40,'Atum - 250g', 4.50);
insert into mercadoria (id,descricao,preco) values (41,'Maionese - 250g', 8.97);
insert into mercadoria (id,descricao,preco) values (42,'Molho de Pimenta - 100g', 12.34);
insert into mercadoria (id,descricao,preco) values (43,'Ervilha - 350g', 4.50);
insert into mercadoria (id,descricao,preco) values (44,'Milho Verde - 350g', 5.67);
insert into mercadoria (id,descricao,preco) values (45,'Seleta - 350g', 2.34);
insert into mercadoria (id,descricao,preco) values (46,'Doce de Leite - 200g', 0.89);
insert into mercadoria (id,descricao,preco) values (47,'Goiabada - 300g', 3.89);
insert into mercadoria (id,descricao,preco) values (48,'Milho de Pipoca - 300g', 3.89);
insert into mercadoria (id,descricao,preco) values (49,'Óleo de cozinha - 1L', 18.45);
insert into mercadoria (id,descricao,preco) values (50,'Leite em Pó - 500g', 19.40);
insert into mercadoria (id,descricao,preco) values (51,'Creme de Leite - 350g', 8.70);
insert into mercadoria (id,descricao,preco) values (52,'Leite Condensado - 350g', 12.10);
insert into mercadoria (id,descricao,preco) values (53,'Pão de Forma - 400g', 3.20);
insert into mercadoria (id,descricao,preco) values (54,'Alface - Un', 9.94);
insert into mercadoria (id,descricao,preco) values (55,'Couve - Un', 1.20);
insert into mercadoria (id,descricao,preco) values (56,'Batata - 1kg', 1.13);
insert into mercadoria (id,descricao,preco) values (57,'Tomate - 1kg', 2.00);
insert into mercadoria (id,descricao,preco) values (58,'Cenoura - 500g', 1.50);
insert into mercadoria (id,descricao,preco) values (59,'Beterraba - 500g', 13.45);
insert into mercadoria (id,descricao,preco) values (60,'Chicória - Un', 14.13);
insert into mercadoria (id,descricao,preco) values (61,'Mandioca - 1kg', 3.50);
insert into mercadoria (id,descricao,preco) values (62,'Chuchu - 500g', 15.89);
insert into mercadoria (id,descricao,preco) values (63,'Espinafre - Un', 6.80);
insert into mercadoria (id,descricao,preco) values (64,'Banana - 500g', 8.90);
insert into mercadoria (id,descricao,preco) values (65,'Ovos - Dz', 4.5);
insert into mercadoria (id,descricao,preco) values (66,'Uva - 1kg', 8.97);
insert into mercadoria (id,descricao,preco) values (67,'Abacate - Un', 12.34);
insert into mercadoria (id,descricao,preco) values (68,'Mamão - Un', 4.50);
insert into mercadoria (id,descricao,preco) values (69,'Melância - Un', 5.67);
insert into mercadoria (id,descricao,preco) values (70,'Melão - Un', 2.34);
insert into mercadoria (id,descricao,preco) values (71,'Jiló - 350g', 0.89);
insert into mercadoria (id,descricao,preco) values (72,'Quiabo - 300g', 3.89);
insert into mercadoria (id,descricao,preco) values (73,'Salsa - Un', 3.89);
insert into mercadoria (id,descricao,preco) values (74,'Cheiro Verde - Un', 18.45);
insert into mercadoria (id,descricao,preco) values (75,'Cebola - 1kg', 19.40);
insert into mercadoria (id,descricao,preco) values (76,'Queijo Minas - 400g', 8.70);
insert into mercadoria (id,descricao,preco) values (77,'Queijo Mussarela - 300g', 12.10);
insert into mercadoria (id,descricao,preco) values (78,'Queijo Outros - 300g', 3.20);
insert into mercadoria (id,descricao,preco) values (79,'Manteiga - 250g', 9.94);
insert into mercadoria (id,descricao,preco) values (80,'Margarina - 250g', 1.20);
insert into mercadoria (id,descricao,preco) values (81,'Iogurte - 500ml', 1.13);
insert into mercadoria (id,descricao,preco) values (82,'Presunto - 300g', 2.00);
insert into mercadoria (id,descricao,preco) values (83,'Peixe - 350g', 1.50);
insert into mercadoria (id,descricao,preco) values (84,'Frango - 1kg', 13.45);
insert into mercadoria (id,descricao,preco) values (85,'Carne Vermelha - 1kg', 14.13);
insert into mercadoria (id,descricao,preco) values (86,'Carne Seca - 500g', 3.50);
insert into mercadoria (id,descricao,preco) values (87,'Salsicha - 500g', 15.89);
*/