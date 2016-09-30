package br.com.saojudas.maven.pontodevenda.model;
import java.text.DecimalFormat;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import br.com.saojudas.maven.pontodevenda.dao.MysqlConnect;

public class ItemCompra{
   
   private int id, idCompra, idMercadoria, quantidade;
   private MysqlConnect db;
   private Connection conn;
   private PreparedStatement st;
   private ResultSet rs;   
   
   //contrutor sem par�metro
   public ItemCompra()
   {
      db = new MysqlConnect();
      conn = db.getConnection();
   } 
   
   //contrutor com par�metro
   public ItemCompra( int idMercadoria, int quantidade)
   {
      setIdMercadoria(idMercadoria);
      setQuantidade(quantidade);
      db = new MysqlConnect();
      conn = db.getConnection();
   }      
         
   //modificador id do item da compra
   public void setIdItemCompra( int id )
   {
      this.id = id;
   }
    
   //modificador id da compra
   public void setIdCompra( int idCompra )
   {
      this.idCompra = idCompra;
   }

   //modificador idMercadoria
   public void setIdMercadoria( int idMercadoria )
   { 
      if(idMercadoria <= 0)
      {
         throw new RuntimeException("O 'C�digo Mercadoria' n�o pode ser menor ou igual a zero!");
      }else
      {
         this.idMercadoria = idMercadoria;
      }          
   }
   
   //modificador quantidade
   public void setQuantidade( int quantidade )
   {  
      if(quantidade <= 0)
      {
         throw new RuntimeException("A 'Quantidade Mercadoria' n�o pode ser menor ou igual a zero!");
      }else
      {
         this.quantidade = quantidade;
      }
   }
   
   //acesso id do item da compra
   public int getIdItemCompra()
   {
      return id;
   }
   
   //acesso id da compra
   public int getIdCompra()
   {
      return idCompra;
   }
   
   //acesso idMercadoria
   public int getIdMercadoria()
   {
      return idMercadoria;
   }
   
   //acesso quantidade
   public int getQuantidade()
   {
      return quantidade;
   }
     
   //atualiza id do item da compra
   public void atualizaIdItemCompra()
   {
      int resultado = 0;
      try 
      {         
         String sql = "select max(id) from itemcompra" ;
         st = conn.prepareStatement(sql);
         rs =  st.executeQuery();
         
         while(rs.next()){
            resultado = rs.getInt(1);
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }
      setIdItemCompra(resultado);
   }
   
   //m�todo para incluir nova compra
   public void incluirItemCompra()
   {  
      try{
         String sql = "insert into itemcompra (idcompra, idmercadoria, quantidade) values (?,?,?)";
         st = conn.prepareStatement(sql);
         st.setInt(1,getIdCompra());
         st.setInt(2,getIdMercadoria());
         st.setInt(3,getQuantidade());
         st.executeUpdate();
         st.close();         
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(null,
                                          "Erro! 'C�digo Mercadoria' ou 'Quantidade Mercadoria' invalido! \nConsulte em 'Exibir Mercadorias'!",
                                          "Erro!",
                                          JOptionPane.ERROR_MESSAGE);
;
      }
      db.closeConnection();      
   }
   
   //m�todo para cancelar o ultimo item compra
   public void cancelarItemCompra()
   {
      try{
         String sql = "delete from itemcompra where id = ? and idcompra = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getIdItemCompra());
         st.setInt(2,getIdCompra());
         
         st.executeUpdate();
         st.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
      db.closeConnection();      
   }
   
   //m�todo acessa o ultimo item compra
   public String acessaUltimoItemCompra()
   {  
      DecimalFormat duas = new DecimalFormat("0.00");
      String resultado = "";
      String sql = "";
      PreparedStatement st;
      ResultSet rs;
      
      try{
         sql = "select * from itemcompra as i left join mercadoria as m on i.idmercadoria = m.id where i.id = ? and i.idcompra = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getIdItemCompra());
         st.setInt(2,getIdCompra());         
         rs = st.executeQuery();
         while(rs.next())
         {
            resultado = rs.getString("descricao") + "\t" + "qtd: " + rs.getInt("quantidade") + " x" + " R$ " + duas.format(rs.getDouble("preco")) + "/un = - R$ " + duas.format( (double)rs.getInt("quantidade") * rs.getDouble("preco"));
         }
         db.closeConnection();      
      }catch(SQLException e)
      {
         e.printStackTrace();      
      }      
      return resultado;      
   }
   
   //acessa o valor total do ultimo id de compra
   public double getTotalUltimoIdCompra()
   {  
      double total = 0;
      String sql = "";
      PreparedStatement st;
      ResultSet rs;
      String resultado = "";
      try 
      {
         sql = "select * from itemcompra as i left join mercadoria as m on i.idmercadoria = m.id where idcompra = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getIdCompra());
         rs = st.executeQuery();
         while(rs.next())
         {
            total += (double)rs.getInt("quantidade") * rs.getDouble("preco");
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();      
      }      
      return total;
   }
   
   //acessa o valor total do ultimo id de compra
   public int getQtdTotalUltimoIdCompra()
   {  
      int total = 0;
      String sql = "";
      PreparedStatement st;
      ResultSet rs;
      String resultado = "";
      try 
      {
         sql = "select * from itemcompra as i left join mercadoria as m on i.idmercadoria = m.id where idcompra = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getIdCompra());
         rs = st.executeQuery();
         while(rs.next())
         {
            total += rs.getInt("quantidade");
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();      
      }      
      return total;
   }
   
   //acessa as 10 mercadorias mais vendidas
   public String get10MaisVendidas()
   {  
      String resultado = "";
      int cont = 1;
      String sql = "";
      PreparedStatement st;
      ResultSet rs;
      try 
      {
         sql = "select m.descricao as descricao, i.idmercadoria as id, sum(i.quantidade) as qtd from itemcompra as i left join mercadoria as m on i.idmercadoria = m.id group by i.idmercadoria order by sum(i.quantidade) desc";
         st = conn.prepareStatement(sql); 
         rs = st.executeQuery();
         while(rs.next() && cont <= 10)
         {  
            resultado += (cont++) + ") " + rs.getString("descricao") + " - Cod: " + rs.getInt("id") + " - Qtd vendida: " + rs.getInt("qtd") + " unidades" + "\n";
         }
      }
      catch(SQLException e)
      {
         JOptionPane.showMessageDialog(null,
                                          "Erro! N�o temos produtos suficientes para realizar essa busca!",
                                          "Erro!",
                                          JOptionPane.ERROR_MESSAGE);      
      }      
      db.closeConnection();
      return resultado;
   }
   
   //m�todo para cancelar compra
   public void cancelarTodosItensCompra()
   {
      try{
         String sql = "delete from itemcompra where idcompra = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,getIdCompra());         
         st.executeUpdate();
         st.close();
      }
      catch(Exception e){
         e.printStackTrace();
      }
      db.closeConnection();      
   }
   
}//fim da classe