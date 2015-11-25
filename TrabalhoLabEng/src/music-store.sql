CREATE DATABASE music_store;
USE music_store;
--pesquisar sequence
--sq_indentity_cliente.curval/nextval

CREATE TABLE cliente (
  codigo INT NOT NULL,
  nome VARCHAR(200) NOT NULL,
  data_nascimento DATE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  logradouro  VARCHAR(100) NOT NULL,
  numero VARCHAR(10) NOT NULL,
  bairro VARCHAR(100) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  cidade VARCHAR(50) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  ddd VARCHAR(2) NOT NULL,
  telefone VARCHAR(9) NOT NULL,
  usuario VARCHAR(50) UNIQUE NOT NULL,
  senha VARCHAR(50) NOT NULL
);

CREATE TABLE venda (
  codigo INT NOT NULL,
  data_venda DATE NOT NULL,
  valor FLOAT NOT NULL,
  cliente_venda INT NOT NULL,
  pagamento VARCHAR(1) NOT NULL
);

CREATE TABLE item_venda (
  cod_venda INT NOT NULL,
  cod_produto INT NOT NULL,
  quantidade INT NOT NULL
);

CREATE TABLE categoria (
  codigo INT NOT NULL,
  nome VARCHAR(50) NOT NULL,
  supercategoria INT
);

CREATE TABLE produto (
  codigo INT NOT NULL,
  nome VARCHAR(500) NOT NULL,
  descricao VARCHAR(500) NOT NULL,
  especificacao VARCHAR(500) NOT NULL,
  codigo_categoria INT NOT NULL,
  codigo_estoque INT NOT NULL
);

CREATE TABLE estoque (
  codigo INT NOT NULL,
  valor_custo FLOAT NOT NULL,
  valor_venda FLOAT NOT NULL,
  quantidade INT NOT NULL
);


--------------------------------------------------- CONSTRAINTS ----------------------------------------------------


-- CONSTRAINT cliente
ALTER TABLE cliente ADD CONSTRAINT pk_cliente PRIMARY KEY(codigo);

-- CONSTRAINT venda
ALTER TABLE venda ADD CONSTRAINT pk_venda PRIMARY KEY (codigo);
ALTER TABLE venda ADD CONSTRAINT fk_cliente_venda FOREIGN KEY (cliente_venda) REFERENCES cliente (codigo);

--CONSTRAINT item_venda
ALTER TABLE item_venda ADD CONSTRAINT pk_item_venda PRIMARY KEY (cod_venda, cod_produto);
ALTER TABLE item_venda ADD CONSTRAINT fk_produto_item_venda FOREIGN KEY (cod_produto) REFERENCES produto (codigo);
ALTER TABLE item_venda ADD CONSTRAINT fk_venda_item_venda FOREIGN KEY (cod_venda) REFERENCES venda (codigo);

--CONSTRAINT categoria
ALTER TABLE categoria  ADD CONSTRAINT pk_categoria PRIMARY KEY (codigo);
ALTER TABLE categoria ADD CONSTRAINT fk_categoria FOREIGN KEY (supercategoria) REFERENCES categoria (codigo);

--CONSTRAINT produto
ALTER TABLE produto ADD CONSTRAINT pk_produto PRIMARY KEY (codigo);
ALTER TABLE produto ADD CONSTRAINT fk_estoque_produto FOREIGN KEY (codigo_estoque) REFERENCES estoque (codigo) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE produto ADD CONSTRAINT fk_categoria_produto FOREIGN KEY (codigo_categoria) REFERENCES categoria (codigo) ON DELETE CASCADE ON UPDATE CASCADE

--CONSTRAINT estoque
ALTER TABLE estoque ADD CONSTRAINT pk_estoque PRIMARY KEY (codigo);



--------------------------------------------- INSERTS --------------------------------------------------------------


--INSERT cliente

INSERT INTO cliente (codigo, nome, data_nascimento, email, cpf, logradouro, numero, bairro, cep, cidade, estado, ddd, telefone, usuario, senha)
	VALUES (1, 'Alberto Farias', TO_DATE('12/03/1996','mm/dd/yyyy'), 'albertofarias@email.com' ,'11353857778', 'Travessa Fernando Ferreira Lopes', '15', 'Oficinas', '84045065', 'Ponta Grossa','PR', '50', '26464646', 'albertofarias', '30125' );
INSERT INTO cliente (codigo, nome, data_nascimento, email, cpf, logradouro, numero, bairro, cep, cidade, estado, ddd, telefone, usuario, senha)
	VALUES (2, 'Marta Moura', TO_DATE('11/04/1990','mm/dd/yyyy'), 'martamoura@email.com','07727742422', 'Rua Projetada C', '80', 'Vila Santa Cruz', '25260074', 'Duque de Caxias' ,  'RJ', '14', '48952017', 'martamoura', 'd45d' );
INSERT INTO cliente (codigo, nome, data_nascimento, email, cpf, logradouro, numero, bairro, cep, cidade, estado, ddd, telefone, usuario, senha)
	VALUES (3, 'Rubens Lima', TO_DATE('10/05/1985','mm/dd/yyyy'), 'rubenslima@email.com', '75741351344', 'Rua Professora Therezinha de Toledo Cassiano', '358', 'Residencial Village Campo Novo', '17048791', 'Bauru', 'SP', '11', '30867895', 'rubenslima', 'asdad' );
INSERT INTO cliente (codigo, nome, data_nascimento, email, cpf, logradouro, numero, bairro, cep, cidade, estado, ddd, telefone, usuario, senha)
	VALUES (4, 'Marcia Oliveira', TO_DATE('09/06/1878','mm/dd/yyyy'), 'marciaoliveira@email.com','07727742422', 'Rua Quarenta e Quatro', '1054', 'Zumbi do Pacheco', '54230060', 'Jaboatão dos Guararapes' ,'PE', '86', '985987654', 'marciaoliveira', 'we5w54e' );
INSERT INTO cliente (codigo, nome, data_nascimento, email,cpf, logradouro, numero, bairro, cep, cidade, estado, ddd, telefone, usuario, senha)
	VALUES (5, 'Ricardo Montes', TO_DATE('08/07/1991','mm/dd/yyyy'), 'ricardomontes@email.com' ,'66244937201', 'Rua Antônio Cubas', '1578', 'Vila Guiomar', '09090440', 'Santo André', 'SP', '11', '30867895', 'ricardomontes', 'jajaj' );	
	
-- INSERT venda

INSERT INTO venda (codigo, data_venda, valor, cliente_venda, pagamento)
	VALUES (1, TO_DATE('10/14/2015','mm/dd/yyyy'), 458.98, 5, 1);
INSERT INTO venda (codigo, data_venda, valor,cliente_venda, pagamento)
	VALUES (2, TO_DATE('11/14/2015','mm/dd/yyyy'), 100.00, 5, 1);
INSERT INTO venda (codigo, data_venda, valor,cliente_venda, pagamento)
	VALUES (3, TO_DATE('11/03/2015','mm/dd/yyyy'), 58.50, 4, 1);
INSERT INTO venda (codigo, data_venda, valor,cliente_venda, pagamento)
	VALUES (4, TO_DATE('08/14/2015','mm/dd/yyyy'), 45.00, 3, 0);
INSERT INTO venda (codigo, data_venda, valor,cliente_venda, pagamento)
	VALUES (5, TO_DATE('09/14/2015','mm/dd/yyyy'), 154.00, 2, 1);	
	
--INSERT item_venda

INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (1, 1, 1);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (1, 3, 10);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (2, 5, 3);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (3, 7, 2);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (1, 9, 1);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (4, 10, 7);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (5, 8, 1);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (5, 6, 4);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (5, 4, 2);
INSERT INTO item_venda (cod_venda, cod_produto, quantidade)
	VALUES (3, 2, 6);	
	
-- INSERT categorias

INSERT INTO categoria (codigo, nome, supercategoria)
	VALUES (1, 'Sopro', NULL);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (10, 'Flauta', 1);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (11, 'Saxofone', 1);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (12, 'Clarinete', 1);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (13, 'Trompete', 1);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (14, 'Trombone', 1);
INSERT INTO categoria (codigo, nome, supercategoria)
	VALUES (2, 'Percursão', NULL);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (20, 'Bateria', 2);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (21, 'Bateria Eletrônica', 2);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (22, 'Cajón', 2);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (23, 'Pandeiro', 2);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (24, 'Atabaque', 2);
INSERT INTO categoria (codigo, nome, supercategoria)
	VALUES (3, 'Cordas', NULL);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (30, 'Violão', 3);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (31, 'Guitarra', 3);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (32, 'Baixo', 3);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (33, 'Cavaco', 3);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (34, 'Banjo', 3);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (35, 'Ukulele', 3);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (36, 'Baixolão', 3);
INSERT INTO categoria (codigo, nome, supercategoria)
	VALUES (4, 'Teclas', NULL);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (40, 'Acordeon/Sanfona', 4);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (41, 'Teclado', 4);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (42, 'Piano', 4);
INSERT INTO categoria (codigo, nome, supercategoria)
	VALUES (5, 'Áudio', NULL);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (50, 'Microfone', 5);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (51, 'Fone', 5);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (52, 'Mesa Analógica', 5);
INSERT INTO categoria (codigo, nome, supercategoria)
	VALUES (6, 'Acessórios', NULL);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (61, 'Sopro', 6);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (62, 'Percursão', 6);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (63, 'Cordas', 6);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (64, 'Teclas', 6);
				INSERT INTO categoria (codigo, nome, supercategoria)
					VALUES (65, 'Áudio', 6);

--INSERT produto AND estoque

--INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
--	VALUES (1, 1, 1, 1);
--INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
--	VALUES (1, '', '', '', 1, 1);


INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
	VALUES (1, 1200.00, 1473.00, 15);
INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
	VALUES (1, 'Guitarra SG Epiphone Special Gothic', 'A história da Epiphone com o lendário guitarrista e inovador Les Paul iniciou no começo dos anos 40...', 'Modelo: SG Special; Código Fornecedor: 10030539; Número de Cordas: 06; Corte do Corpo: SG; Ferragens: Cromadas', 31, 1);
INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
	VALUES (2, 4800.00, 5361.00, 5);
INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
	VALUES (2, 'Guitarra Strato Fender Standard ', 'Guitarra Strato Fender Standard A Fender a maior fabricante de guitarras, baixos e equipamentos relacionados do mundo...', 'Modelo: Standard; Formato do Corpo: Strato; Número de Cordas: 6; Corpo: Alder; Braço: Maple "modern C" shape envernizado', 31, 2);
INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
	VALUES (3, 1100.00, 1400.00, 2);
INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
	VALUES (3, 'Guitarra Explorer Strinberg CLG 48', 'Guitarra Explorer Strinberg CLG 48A Strinberg uma marca de instrumentos de cordas, como guitarras, contrabaixos, violões e baixolões que vem a cada ano ganhando espaço no cenário nacional.', 'Modelo: CLG 48; Corpo: Basswood; Braço: Maple; Escala: Rosewood; Captadores: 2 Humbucker; Controles: 1V, 1T', 31, 3);

	
INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
	VALUES (4, 1, 1, 10);
INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
	VALUES (4, 'Contrabaixo 6C Ativo Strinberg CLB 26 A', 'A Strinberg uma marca de instrumentos de cordas, como guitarras, contrabaixos, violões e baixolões que vem a cada ano ganhando...', 'Modelo: CLB 26A; Corpo: Basswood; Tipo: Elôtrico Ativo; Braço: Maple', 32, 4);
INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
	VALUES (5, 1, 1, 5);
INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
	VALUES (5, 'Contrabaixo 5C Ativo Sterling S.U.B. Ray 5 ', 'Contrabaixo Ativo 5C Sterling by Music Man S.U.B. Ray 5 A família da série S.U.B. de instrumentos...', 'Modelo: S.U.B. Ray 5; Código Fornecedor: 10320028; Número de cordas: 5; Corpo: Hardwood', 32, 5);
INSERT INTO estoque (codigo,valor_custo ,valor_venda ,quantidade) 
	VALUES (6, 1, 1, 8);
INSERT INTO produto (codigo, nome ,descricao ,especificacao ,codigo_categoria ,codigo_estoque)
	VALUES (6, 'Contrabaixo Passivo 4C Epiphone Thunderbird IV', 'Contrabaixo Passivo 4C Epiphone Thunderbird IVA história da Epiphone com o lendário...', 'Modelo: Thunderbird IV;Código Fornecedor: 10030352; Nímero de cordas: 4; Corpo: Mahogany', 32, 6);	
	

update estoque set
	quantidade = (
		select 
			case 
			when sum(quantidade) > 0 then 
				quantidade - 1 
			else 
				quantidade
			end as nova_quantidade
		from estoque where codigo = 1
		group by quantidade
		)
from estoque where codigo = 1;

select * from estoque where codigo = 1;

select 
	case 
	when sum(quantidade) > 0 then 
		quantidade - 1 
	else 
		quantidade
	end as nova_quantidade
from estoque where codigo = 1
group by quantidade

select * from produto 
	inner join estoque 
	on produto.codigo_estoque = estoque.codigo 
	where produto.nome like '%Guitarra S%';
	
select * from categoria c 
	inner join produto p 
	on ( c.codigo = p.codigo_categoria )
	inner join estoque e
	on (p.codigo_estoque = e.codigo ) 
	where p.codigo =  1

select * from cliente
where usuario = 'rubenslima' and senha = 'asdad';

-- query oracle
--select * from venda 
--  where EXTRACT(month FROM venda.data_venda) = 09;
	
-- query oracle e sql server	
select p.nome, SUM(iv.quantidade) as mais_vendido 
	from item_venda iv
	inner join produto p
	on iv.cod_produto = p.codigo
	group by p.nome
	order by mais_vendido asc	
  
  select EXTRACT(month FROM sysdate) from dual;
	
--http://pt.stackoverflow.com/questions/7626/como-pegar-o-ano-atual-no-oracle	
