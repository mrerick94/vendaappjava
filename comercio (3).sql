-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11-Jul-2019 às 23:18
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `comercio`
--
CREATE DATABASE IF NOT EXISTS `comercio` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `comercio`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `nome`) VALUES
(3, 'Verduras'),
(4, 'Frutas'),
(5, 'Frios'),
(6, 'Carnes'),
(7, 'Utensilios'),
(8, 'Bebidas'),
(17, 'Legumes'),
(18, 'Guloseimas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(145) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `dataCadastro` date NOT NULL,
  `endereco_id` int(11) DEFAULT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cpf`, `dataCadastro`, `endereco_id`, `ativo`) VALUES
(1, 'qwerqwerqewr', '3412423434', '2019-07-04', NULL, 0),
(2, 'qwererqwer', '42134245', '2019-07-04', NULL, 1),
(3, 'Erick', '12342315', '2019-07-04', 7, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `dataCompra` date NOT NULL,
  `fornecedor_id` int(11) NOT NULL,
  `dataVencimento` date DEFAULT NULL,
  `dataPagamento` date DEFAULT NULL,
  `valorPago` double(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `compra`
--

INSERT INTO `compra` (`id`, `dataCompra`, `fornecedor_id`, `dataVencimento`, `dataPagamento`, `valorPago`) VALUES
(1, '2019-07-07', 4, NULL, NULL, NULL),
(2, '2019-07-03', 4, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `compraitem`
--

CREATE TABLE `compraitem` (
  `id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `compra_id` int(11) NOT NULL,
  `quantidade` double NOT NULL,
  `precoCompra` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `compraitem`
--

INSERT INTO `compraitem` (`id`, `produto_id`, `compra_id`, `quantidade`, `precoCompra`) VALUES
(1, 9, 1, 7, 1.3),
(2, 10, 2, 300, 1.5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id` int(11) NOT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `pais` varchar(45) NOT NULL,
  `uf` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `rua` varchar(70) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id`, `cep`, `pais`, `uf`, `cidade`, `bairro`, `rua`, `numero`, `complemento`) VALUES
(1, NULL, 'Brasil', 'SC', 'Palhoça', 'Ponte do Imaruim', 'Alameda dos Ipes', 73, ''),
(2, NULL, 'Robsone', 'SC', 'Robsolandia', 'Robsonaria', 'Robsoneiro', 99, ''),
(3, NULL, 'robson', 'SC', 'robson', 'robson', 'robson', 88, ''),
(4, NULL, 'robson', 'SP', 'robson', 'robson', 'robson', 88, ''),
(5, NULL, 'brenner', 'SC', 'brenner', 'brenner', 'brenner', 55, ''),
(6, NULL, 'robson', 'SC', 'robson', 'robson', 'robson', 44, 'robson'),
(7, '3123134', 'Brasil', 'Santa Catarina – SC', 'Palhoça', 'wqerqrqwetetet', 'ewqrwerwqerwqert', 423, ''),
(8, '88130-742', 'Brasil', 'Santa Catarina – SC', 'Palhoça', 'Ponte do Imaruim', 'Alameda dos Ipês', 73, '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `formapagamento`
--

CREATE TABLE `formapagamento` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `formapagamento`
--

INSERT INTO `formapagamento` (`id`, `nome`) VALUES
(1, 'Cartão de Crédito'),
(2, 'Dinheiro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `id` int(11) NOT NULL,
  `nome` varchar(145) NOT NULL,
  `dataCadastro` date NOT NULL,
  `endereco_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`id`, `nome`, `dataCadastro`, `endereco_id`, `usuario_id`, `ativo`) VALUES
(1, 'Erick', '2019-06-29', 1, 2, 0),
(4, 'brenner25', '2019-07-01', 5, 6, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil`
--

CREATE TABLE `perfil` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `perfil`
--

INSERT INTO `perfil` (`id`, `nome`) VALUES
(1, 'Administrador'),
(2, 'Vendedor'),
(3, 'Assistente'),
(4, 'Fornecedor');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `codigobarra` varchar(30) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `precoCompra` double(7,2) NOT NULL,
  `precoVenda` double(7,2) NOT NULL,
  `estoque` double NOT NULL DEFAULT '0',
  `categoria_id` int(11) NOT NULL,
  `fornecedor_id` int(11) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `codigobarra`, `nome`, `precoCompra`, `precoVenda`, `estoque`, `categoria_id`, `fornecedor_id`, `ativo`) VALUES
(1, '41234235', 'Batata', 1.30, 2.50, 1200, 3, 1, 1),
(2, '13423153', 'Banana', 1.00, 2.00, 1000, 4, 4, 1),
(9, '341324213532423', 'Chocolate', 1.30, 3.00, 0, 18, 4, 1),
(10, '14522314235', 'Macarrao', 2.00, 4.00, 0, 17, 4, 1),
(11, '23\'144', 'weqrqwerqwe', 3.00, 5.00, 0, 3, 4, 1),
(12, '41324234', 'wqerwer', 3.00, 4.00, 0, 3, 4, 1),
(13, '42134234', 'qwerweqr', 3.00, 5.00, 0, 3, 4, 1),
(14, '2134', 'rqwerqwer', 3.00, 6.00, 1, 3, 4, 1),
(15, '4123423', 'eweeeeeeee', 2.00, 6.00, 0, 3, 4, 1),
(16, '789318471984', 'ErickTeste', 300.00, 500.00, 0, 8, 4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `salario` double(12,2) DEFAULT NULL,
  `perfil_id` int(11) NOT NULL,
  `endereco_id` int(11) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `salario`, `perfil_id`, `endereco_id`, `ativo`) VALUES
(1, 'admin', 'admin@admin.com', 'admin', NULL, 1, 1, 1),
(2, 'Erick', 'erick@erick.com', 'erick', 1300.00, 3, 1, 0),
(3, 'vendedor', 'vendedor@vendedor.com', 'vendedor', NULL, 2, 1, 1),
(6, 'brenner25', 'brenner@brenner.com', 'brenner', 0.00, 4, 5, 1),
(7, 'Bunda', 'bunda@bunda.com', 'bunda', 1200.00, 1, 8, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `dataVenda` date NOT NULL,
  `dataVencimento` date DEFAULT NULL,
  `dataPagamento` date DEFAULT NULL,
  `valorPago` double(12,2) DEFAULT NULL,
  `formaPagamento_id` int(11) NOT NULL,
  `cliente_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`id`, `dataVenda`, `dataVencimento`, `dataPagamento`, `valorPago`, `formaPagamento_id`, `cliente_id`) VALUES
(5, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(6, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(7, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(8, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(9, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(10, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(11, '2019-07-11', '2019-07-11', '2019-07-11', 6.00, 1, NULL),
(12, '2019-07-11', '2019-07-11', '2019-07-11', 1006.00, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendaitem`
--

CREATE TABLE `vendaitem` (
  `id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `venda_id` int(11) NOT NULL,
  `quantidade` double NOT NULL,
  `precoVenda` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `vendaitem`
--

INSERT INTO `vendaitem` (`id`, `produto_id`, `venda_id`, `quantidade`, `precoVenda`) VALUES
(1, 14, 11, 1, 6),
(2, 14, 12, 1, 6),
(3, 16, 12, 2, 500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cliente_endereco1_idx` (`endereco_id`);

--
-- Indexes for table `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_compra_fornecedor1_idx` (`fornecedor_id`);

--
-- Indexes for table `compraitem`
--
ALTER TABLE `compraitem`
  ADD PRIMARY KEY (`id`,`produto_id`,`compra_id`),
  ADD KEY `fk_produto_has_compra_compra1_idx` (`compra_id`),
  ADD KEY `fk_produto_has_compra_produto1_idx` (`produto_id`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cep_idx` (`cep`);

--
-- Indexes for table `formapagamento`
--
ALTER TABLE `formapagamento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_fornecedor_endereco1_idx` (`endereco_id`),
  ADD KEY `fk_fornecedor_usuario1_idx` (`usuario_id`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigobarra` (`codigobarra`),
  ADD KEY `fk_produto_categoria1_idx` (`categoria_id`),
  ADD KEY `fk_produto_fornecedor1` (`fornecedor_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_usuario_perfil1_idx` (`perfil_id`),
  ADD KEY `fk_usuario_endereco1` (`endereco_id`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_venda_formaPagamento1_idx` (`formaPagamento_id`),
  ADD KEY `fk_venda_cliente1` (`cliente_id`);

--
-- Indexes for table `vendaitem`
--
ALTER TABLE `vendaitem`
  ADD PRIMARY KEY (`id`,`produto_id`,`venda_id`),
  ADD KEY `fk_produto_has_venda_venda1_idx` (`venda_id`),
  ADD KEY `fk_produto_has_venda_produto1_idx` (`produto_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `compraitem`
--
ALTER TABLE `compraitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `vendaitem`
--
ALTER TABLE `vendaitem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_endereco1` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_compra_fornecedor1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `compraitem`
--
ALTER TABLE `compraitem`
  ADD CONSTRAINT `fk_produto_has_compra_compra1` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_produto_has_compra_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD CONSTRAINT `fk_fornecedor_endereco1` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`),
  ADD CONSTRAINT `fk_fornecedor_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_produto_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_produto_fornecedor1` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_endereco1` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_perfil1` FOREIGN KEY (`perfil_id`) REFERENCES `perfil` (`id`) ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fk_venda_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_venda_formaPagamento1` FOREIGN KEY (`formaPagamento_id`) REFERENCES `formapagamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `vendaitem`
--
ALTER TABLE `vendaitem`
  ADD CONSTRAINT `fk_produto_has_venda_produto1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_produto_has_venda_venda1` FOREIGN KEY (`venda_id`) REFERENCES `venda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
