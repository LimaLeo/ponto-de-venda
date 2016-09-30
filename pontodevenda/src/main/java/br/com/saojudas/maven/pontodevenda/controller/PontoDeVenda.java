package br.com.saojudas.maven.pontodevenda.controller;
import javax.swing.JOptionPane;

import br.com.saojudas.maven.pontodevenda.model.Compra;
import br.com.saojudas.maven.pontodevenda.model.ItemCompra;
import br.com.saojudas.maven.pontodevenda.model.Mercadoria;
import br.com.saojudas.maven.pontodevenda.model.PagamentoCartao;
import br.com.saojudas.maven.pontodevenda.model.PagamentoDinheiro;

public class PontoDeVenda
{  
   private Compra c;
   private ItemCompra i;
   private PagamentoCartao pC;
   private PagamentoDinheiro pD;
   private Mercadoria m;
   public String troco;
   
   //construtor sem par�metro
   public PontoDeVenda()
   {  
      Mercadoria m = new Mercadoria();
      m.verificaMercadoria();
   }
   
   //m�todo nova compra
   public void novaCompra()
   {      
      c = new Compra();
      c.incluirCompra();
   }
   
   //m�todo cancela compra
   public void cancelaCompra()
   {  
      c = new Compra();
      c.atualizaIdCompra();
      
      i = new ItemCompra();
      i.atualizaIdItemCompra();      
      i.setIdCompra( c.getId() );
      
      i.cancelarTodosItensCompra();
      c.cancelarCompra();           
   }
   
   //m�toddo adiciona mercadoria
   public void novoItemCompra( int idMercadoria, int quantidade)
   {  
      c = new Compra();    
      i = new ItemCompra(idMercadoria, quantidade);
      c.atualizaIdCompra();
      i.setIdCompra( c.getId() );    
      try
      {
         i.incluirItemCompra();
      }
      catch(Exception f){
         JOptionPane.showMessageDialog(null,
                                          f.getMessage(),
                                          "Erro!",
                                          JOptionPane.ERROR_MESSAGE);
      }                  
   }
   
   //m�todo cancela mercadoria
   public void excluiItemCompra()
   {    
      c = new Compra();  
      c.atualizaIdCompra();
          
      i = new ItemCompra();
      i.atualizaIdItemCompra();      
      i.setIdCompra( c.getId() );
                      
      i.cancelarItemCompra();
   }
   
   //acessa o ultimo produto cancelado
   public String acessaUltimoItemCompra()
   {    
      c = new Compra();  
      c.atualizaIdCompra();
          
      i = new ItemCompra();
      i.atualizaIdItemCompra();      
      i.setIdCompra( c.getId() );
                      
      return i.acessaUltimoItemCompra();                
   }
   
   //m�todo de acesso descri��o do item compra  
   public String getItemCompraById( int idMercadoria, int quantidade )
   {  
      m = new Mercadoria();      
      return m.getMercadoriaById(idMercadoria, quantidade);
   }
   
   //m�todo de novo pagamento
   public void novoPagamento( int idMercadoria, int quantidade)
   {      
      ItemCompra i = new ItemCompra(idMercadoria, quantidade);
      i.setIdCompra( c.getId() );
   }    
   
   //m�todo compra Cart�o
   public void fecharCompraCartao( boolean cartaoAprovado )
   {
      c = new Compra();  
      c.atualizaIdCompra();
      
      i = new ItemCompra();                
      i.setIdCompra( c.getId() );  
      i.atualizaIdItemCompra();              
      
      pC = new PagamentoCartao(i.getTotalUltimoIdCompra());  
      pC.setIdCompra( c.getId() ); 
         
      boolean continuar = false;
      
      do
      {                                                  
         try
         {  
            long numeroCartao = Long.parseLong(JOptionPane.showInputDialog("informe o n�mero do cart�o: "));
            pC.setNumeroCartao(numeroCartao);
            continuar = false;            
         }
         catch(Exception f){
            JOptionPane.showMessageDialog(null,
                                             f.getMessage(),
                                             "Erro!",
                                             JOptionPane.ERROR_MESSAGE);
           continuar = true;
         } 
      }while(continuar);
      do
      {     
         String validadeCartao = JOptionPane.showInputDialog("Informe a validade do cart�o: ");      
         
         try
         {  
            pC.setValidadeCartao(validadeCartao);
            continuar = false;            
         }
         catch(Exception f){
            JOptionPane.showMessageDialog(null,
                                             f.getMessage(),
                                             "Erro!",
                                             JOptionPane.ERROR_MESSAGE);
           continuar = true;
         }
      }while(continuar);
      do
      {   
         try
         {  
            if( cartaoAprovado )
            {
               pC.incluirPagamento();
               continuar = false;  
            }        
         }
         catch(Exception f){
            JOptionPane.showMessageDialog(null,
                                             f.getMessage(),
                                             "Erro!",
                                             JOptionPane.ERROR_MESSAGE);
           continuar = true;
         }
      }while(continuar);              
   }
   
   //verifica se o cart�o foi aprovado
   public boolean cartaoAprovado()
   {
      pC = new PagamentoCartao();
      return pC.cartaoAprovado();     
   }
   
   //m�todo compra dinheiro
   public void fecharCompraDinheiro()
   {
      c = new Compra();  
      c.atualizaIdCompra();
      
      i = new ItemCompra();                
      i.setIdCompra( c.getId() );  
      i.atualizaIdItemCompra();              
      
      pD = new PagamentoDinheiro(i.getTotalUltimoIdCompra());  
      pD.setIdCompra( c.getId() ); 
         
      boolean continuar = false;
      
      do
      {                                                   
         try
         {  
            double valorRecebido = Double.parseDouble(JOptionPane.showInputDialog("informe o valor recebido: "));
            pD.setValorRecebido(valorRecebido);
            continuar = false;            
         }
         catch(Exception f){
            JOptionPane.showMessageDialog(null,
                                             f.getMessage(),
                                             "Erro!",
                                             JOptionPane.ERROR_MESSAGE);
           continuar = true;
         } 
      }while(continuar);
      
      do
      {   
         try
         {  
            pD.incluirPagamento();
            continuar = false;     
         }
         catch(Exception f){
            JOptionPane.showMessageDialog(null,
                                             f.getMessage(),
                                             "Erro!",
                                             JOptionPane.ERROR_MESSAGE);
           continuar = true;
         }
      }while(continuar);      
      
      setTroco( pD.getTroco()) ;        
   }
   
   // modificador troco
   public void setTroco( String troco )
   {
      this.troco = troco;
   }
   
   //acesso troco
   public String getTroco()
   {
      return troco;
   }
     
   //m�todo que retorna o valor total da compra
   public double totalDaCompra()
   {     
      c = new Compra();  
      c.atualizaIdCompra();
      
      i = new ItemCompra(); 
               
      i.setIdCompra( c.getId() );  
      i.atualizaIdItemCompra();              
               
      return i.getTotalUltimoIdCompra();
   }
   
   //m�todo que quantidade total da compra
   public int qtdTotalDaCompra()
   {     
      c = new Compra();  
      c.atualizaIdCompra();
      
      i = new ItemCompra(); 
               
      i.setIdCompra( c.getId() );  
      i.atualizaIdItemCompra();              
               
      return i.getQtdTotalUltimoIdCompra();
   }
   
   //m�todo que retorna os 10 produtos mais vendidos
   public String get10MaisVendidas()
   {     
      c = new Compra();  
      i = new ItemCompra();          
      i.setIdCompra( c.getId() );                
               
      return i.get10MaisVendidas();
   }
}