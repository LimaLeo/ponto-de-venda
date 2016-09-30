package br.com.saojudas.maven.pontodevenda.view;
import javax.swing.*;

import br.com.saojudas.maven.pontodevenda.model.Mercadoria;

import java.awt.*;

public class MercadoriaGUI extends JFrame 
{  
   private JTextArea taProdutos;
   private JScrollPane scProduto;
   
   public MercadoriaGUI()
   {
      super("MercadoUSJT - v.1.0"); 
      
      //instancia o objeto border layout
      Container c = getContentPane();
      c.setLayout(new BorderLayout());      
      
      //instamcia JTextArea com JScroolPAne
      taProdutos = new JTextArea();
      scProduto = new JScrollPane(taProdutos);
      
      //atribui o JScrollPane no centro do border layout
      c.add(BorderLayout.CENTER, scProduto);
      taProdutos.setEditable(false);
      
      //instancia mercadoria
      Mercadoria mercadoria = new Mercadoria();
      
      //coloca os produtos da classe mercadoria dentro do JTextArea
      taProdutos.setText(mercadoria.getAllMercadoria());
      setSize(400, 500);
      setLocation(450, 200);
      setVisible(true);
   }

}