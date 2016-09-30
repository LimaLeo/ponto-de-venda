package br.com.saojudas.maven.pontodevenda.model;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import br.com.saojudas.maven.pontodevenda.dao.MysqlConnect;

public class Mercadoria
{
   private int id;
   private String descricao;
   private double preco;
   private MysqlConnect db;
   private Connection conn;
   private ArrayList<String> lista;
   
   public Mercadoria()
   {  
      db = new MysqlConnect();
      conn = db.getConnection();
      lista = new ArrayList<String>();
   }
   
   //modificador id
   public void setId(int id)
   {
      this.id = id;
   } 
   
   //modificador descricao
   public void setDescricao( String descricao )
   {
      this.descricao = descricao;
   }
   
   //modificador preco
   public void setPreco( double preco)
   {
      this.preco = preco;
   }
   
   //acesso id
   public int getId()
   {
      return id;
   }
   
   //acesso descricao
   public String getDescricao()
   {
      return descricao;
   }
   
   //acesso preco
   public double getPreco()
   {
      return preco;
   }
   
   //ArrayList para popular o banco com produtos
   public void inserirMercadorias()
   {            
      lista.add("insert into mercadoria (id,descricao,preco) values (1,'Sab�o em P� - 1kg', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (2,'Amaciante - 500ml',12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (3,'Detergente - 500ml', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (4,'�gua Sanit�ria - 5L', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (5,'Esponja de A�o', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (6,'Buchinha de Pia', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (7,'Sab�o em Pedra', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (8,'Sabonete', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (9,'Shampoo - 250ml', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (10,'Condicionador - 250ml', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (11,'Desinfetante - 1L',3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (12,'Lustra M�veis - 150ml', 15.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (13,'Tira Manchas - 150ml', 6.80)");
      lista.add("insert into mercadoria (id,descricao,preco) values (14,'Limpa Vidros - 150ml', 8.90)");
      lista.add("insert into mercadoria (id,descricao,preco) values (15,'�lcool - 1L', 4.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (16,'Saco de Lixo - 30l', 8.97)");
      lista.add("insert into mercadoria (id,descricao,preco) values (17,'Saco de Lixo - 50l', 12.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (18,'Refrigerante - 2l', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (19,'Suco garrafa - 1l', 5.67)");
      lista.add("insert into mercadoria (id,descricao,preco) values (20,'Suco caixinha - 500ml', 2.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (21,'Suco sach�', 0.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (22,'Leite integral - 1L', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (23,'Leite desnatado - 1L',3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (24,'Arroz - 5kg',18.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (25,'Feij�o - 2kg', 19.40)");
      lista.add("insert into mercadoria (id,descricao,preco) values (26,'Macarr�o - 500g', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (27,'Extrato de Tomate - 350g', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (28,'Molho de Tomate - 350g', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (29,'Sal - 500g', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (30,'A�ucar - 1kg', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (31,'Achocolatado - 500g', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (32,'Bolacha - 200g', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (33,'Caf� - 500g', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (34,'Farofa Pronta - 500g', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (35,'Fub� - 500g', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (36,'Farinha de Trigo - 1kg', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (37,'Farinha de Milho - 500g', 15.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (38,'Farinha de Mandioca - 500g', 6.80)");
      lista.add("insert into mercadoria (id,descricao,preco) values (39,'Sardinha - 250g', 8.90)");
      lista.add("insert into mercadoria (id,descricao,preco) values (40,'Atum - 250g', 4.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (41,'Maionese - 250g', 8.97)");
      lista.add("insert into mercadoria (id,descricao,preco) values (42,'Molho de Pimenta - 100g', 12.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (43,'Ervilha - 350g', 4.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (44,'Milho Verde - 350g', 5.67)");
      lista.add("insert into mercadoria (id,descricao,preco) values (45,'Seleta - 350g', 2.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (46,'Doce de Leite - 200g', 0.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (47,'Goiabada - 300g', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (48,'Milho de Pipoca - 300g', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (49,'�leo de cozinha - 1L', 18.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (50,'Leite em P� - 500g', 19.40)");
      lista.add("insert into mercadoria (id,descricao,preco) values (51,'Creme de Leite - 350g', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (52,'Leite Condensado - 350g', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (53,'P�o de Forma - 400g', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (54,'Alface - Un', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (55,'Couve - Un', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (56,'Batata - 1kg', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (57,'Tomate - 1kg', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (58,'Cenoura - 500g', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (59,'Beterraba - 500g', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (60,'Chic�ria - Un', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (61,'Mandioca - 1kg', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (62,'Chuchu - 500g', 15.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (63,'Espinafre - Un', 6.80)");
      lista.add("insert into mercadoria (id,descricao,preco) values (64,'Banana - 500g', 8.90)");
      lista.add("insert into mercadoria (id,descricao,preco) values (65,'Ovos - Dz', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (66,'Uva - 1kg', 8.97)");
      lista.add("insert into mercadoria (id,descricao,preco) values (67,'Abacate - Un', 12.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (68,'Mam�o - Un', 4.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (69,'Mel�ncia - Un', 5.67)");
      lista.add("insert into mercadoria (id,descricao,preco) values (70,'Mel�o - Un', 2.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (71,'Jil� - 350g', 0.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (72,'Quiabo - 300g', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (73,'Salsa - Un', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (74,'Cheiro Verde - Un', 18.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (75,'Cebola - 1kg', 19.40)");
      lista.add("insert into mercadoria (id,descricao,preco) values (76,'Queijo Minas - 400g', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (77,'Queijo Mussarela - 300g', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (78,'Queijo Outros - 300g', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (79,'Manteiga - 250g', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (80,'Margarina - 250g', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (81,'Iogurte - 500ml', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (82,'Presunto - 300g', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (83,'Peixe - 350g', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (84,'Frango - 1kg', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (85,'Carne Vermelha - 1kg', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (86,'Carne Seca - 500g', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (87,'Salsicha - 500g', 15.89)");
      
      PreparedStatement st = null;      
      ResultSet rs;
      Iterator<String> listaProdutos = lista.iterator();
      String sql = "";   
      
      try 
      {                               
         while( listaProdutos.hasNext() )
         {  
            sql = listaProdutos.next();
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            st.close();             
         }

      }catch(SQLException e)
      {
         e.printStackTrace();      
      }
      
   }
   
   //verifica quantidade de mercadorias
   public void verificaMercadoria()
   {
      String sql = "select count(*) as qtd from mercadoria";
      PreparedStatement st;
      ResultSet rs;
      int qtd = 0;
      
      try
      {
         st = conn.prepareStatement(sql);
         rs = st.executeQuery();
         
         if(rs.next())
         {
            qtd = rs.getInt("qtd");
         }
         if(qtd <= 0)
         {
            inserirMercadorias();
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();      
      }
   }
   
   //acessa em uma String todos os produtos inseridos no banco
   public String getAllMercadoria()
   {
      String sql = "select * from mercadoria order by descricao";
      PreparedStatement st;
      ResultSet rs;
      String resultado = "";
      DecimalFormat duas = new DecimalFormat("0.00");
      try 
      {
         st = conn.prepareStatement(sql);
         rs = st.executeQuery();
         while(rs.next())
         {
            resultado += "C�d :" + rs.getInt("id") + "\t" + rs.getString("descricao") + " /R$ " + duas.format(rs.getDouble("preco")) + "\n";
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();      
      }
      return resultado;      
   }   
   
   //acessa um produto por id
   public String getMercadoriaById(int id, int qtd)
   {  
      String sql = "";
      PreparedStatement st;
      ResultSet rs;
      String resultado = "";
      DecimalFormat duas = new DecimalFormat("0.00");
      try 
      {
         sql = "select * from mercadoria where id = ?";
         st = conn.prepareStatement(sql);
         st.setInt(1,id);
         rs = st.executeQuery();
         while(rs.next())
         {
            resultado += "\n" + rs.getString("descricao") + "\t" + "qtd: " + qtd + " x" + " R$ " + duas.format(rs.getDouble("preco")) + "/un = R$ " + duas.format( (double)qtd *rs.getDouble("preco"));
         }
      }
      catch(SQLException e)
      {
         e.printStackTrace();      
      }      
      return resultado;
   }

}//fim da classe
