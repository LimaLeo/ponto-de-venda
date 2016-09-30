package br.com.saojudas.maven.pontodevenda.model;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.saojudas.maven.pontodevenda.dao.MysqlConnect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Compra
{
   private int id;
   private String data;
   private MysqlConnect db;
   private Connection conn;
   private PreparedStatement st;
   private ResultSet rs;
   private DateFormat dateFormat;
   private Date date;
   
   //construtor sem parametro
   public Compra()
   {  
      db = new MysqlConnect();
      conn = db.getConnection();
      dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      date = new Date(); 
      setData(dateFormat.format(date));
   }
   
   //modificador id 
   public void setId( int id )
   {
      this.id = id;
   }
   
   //modificador data
   public void setData( String data )
   {
      this.data = data;
   }
   
   //acesso id
   public int getId()
   {  
      return id;
   }
   
   //acesso data
   public String getData()
   {
      return data;
   }
         
   //atualiza id da compra
   public void atualizaIdCompra()
   {
      int resultado = 0;
      try 
      {         
         String sql = "select max(id) from compra" ;
         st = conn.prepareStatement(sql);
         rs =  st.executeQuery();
         
         while(rs.next()){
            resultado = rs.getInt(1);
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }
      setId(resultado);
   }
   
   //m�todo para incluir nova compra no banco de dados
   public void incluirCompra()
   {  
      try{
         String sql = "insert into compra (data) values (?)";
         st = conn.prepareStatement(sql);
         st.setString(1,getData());
         st.executeUpdate();
         st.close();         
      }
      catch(Exception e){
         e.printStackTrace();
      }      
      db.closeConnection(); 
   }
   
   //m�todo para excluir ultima compra do banco de dados
   public void cancelarCompra()
   {
      try{
         String sql = "delete from compra where id = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1, getId() );
         st.executeUpdate();
         st.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }   
      db.closeConnection(); 
   }
      
}//fim da classe