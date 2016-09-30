package br.com.saojudas.maven.pontodevenda.model;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.saojudas.maven.pontodevenda.dao.MysqlConnect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Pagamento
{  
   protected int id, idCompra;
   protected String data;
   protected double valor;
   private MysqlConnect db;
   private Connection conn;
   private PreparedStatement st;
   private DateFormat dateFormat;
   private Date date;
   
   //construtor com parametro
   public Pagamento( double valor)
   {
      db = new MysqlConnect();
      conn = db.getConnection();
      dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      date = new Date(); 
      setData(dateFormat.format(date));
      setValor(valor);
   }
   
   //construtor sem parametro
   public Pagamento()
   {  
      db = new MysqlConnect();
      conn = db.getConnection();
   }
   
  //modificador acesso id pagamento
   public void setId( int id )
   {
      this.id = id;
   }
   
   //modificador data
   public void setData( String data)
   {
      this.data = data; 
   }
   
   //modificador valor
   public void setValor( double valor )
   {
      this.valor = valor;
   }
   
   //modificador id compra
   public void setIdCompra( int idCompra )
   {
      this.idCompra = idCompra;
   }
   
   //acesso id pagamento
   public int getId()
   {
      return id;
   }
   
   //data
   public String getData()
   {
      return data;
   }
   
   //acesso valor
   public double getValor()
   {
      return valor;
   }
   
   //metodo de acesso id compra
   public int getIdCompra()
   {
      return idCompra;
   }
   
   //inclui pagamento no banco
   public void incluirPagamento()
   {  
      boolean sucesso = false;
      try{
         String sql = "insert into pagamento (data_pagamento, valor, id_compra) values (?,?,?)";
         st = conn.prepareStatement(sql);
         st.setString(1,getData());
         st.setDouble(2,getValor());
         st.setInt(3,getIdCompra());
         st.executeUpdate();    
         st.close();         
      }
      catch(Exception e){
         e.printStackTrace();
      }
      db.closeConnection();
   }
}