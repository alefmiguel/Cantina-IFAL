package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connections.ConnectionFactory;

public class CreateTable {

    static String tabelaFuncionario = 
    " create table funcionario (  "+
    "codUnico int(4) PRIMARY KEY AUTO_INCREMENT ,"+
    "nome VARCHAR(255),"+
    "senha VARCHAR(255));";

    static String tabelaVenda = "create table venda ("+
    "cod_venda int(4) PRIMARY KEY AUTO_INCREMENT,"+
    "data_venda date,"+
    "desconto decimal(6,2),"+
    "total_venda decimal(6,2),"+
    "forma_pagamento varchar(10));";

    static String tabelaProduto = "create table produto ("+
    "cod_produto int(5) PRIMARY KEY AUTO_INCREMENT,"+
    "nome varchar(255),"+
    "descricao varchar(255), "+
    "preco_compra decimal(6,2), "+
    "preco_venda decimal(6,2),"+
    "qtd_comprada int(4),"+
    "estoque_min int(4));";


    static String tabelaItemVendido = "CREATE TABLE item_vendido("+
        "id int(5) PRIMARY KEY,"+
        "cod_produto int(5),"+
        "cod_venda int(4), "+
        "quantidade int(6),"+
        "FOREIGN KEY (cod_produto) REFERENCES produto(cod_produto),"+
        "FOREIGN KEY (cod_venda) REFERENCES venda(cod_venda));";
    public static void main(String[] args) throws SQLException {
        String create_table_sql = tabelaFuncionario;
        Connection con = ConnectionFactory.getConnection();

        Statement stmt = con.createStatement();
        stmt.execute(create_table_sql);
        
        stmt.close();
        con.close();
    }
}
