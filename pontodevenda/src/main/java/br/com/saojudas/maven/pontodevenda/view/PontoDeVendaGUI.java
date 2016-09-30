package br.com.saojudas.maven.pontodevenda.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

import br.com.saojudas.maven.pontodevenda.controller.PontoDeVenda;

import java.text.DecimalFormat;

public class PontoDeVendaGUI extends JFrame implements ActionListener{
   
   /**
   * Atributos primeira coluna
   **/
   //ponto de venda
   private PontoDeVenda p;
   //bot�es
   private JButton bNovaCompra, bCancelarNovaCompra, bFecharCompra, bFecharPDV, bAdcionaMercado,
            bAdicionaMercadoria, bCancelaMercadoria, bExibirMercadoria, bExibirTop10;
   //textos
   private JLabel lTotalCompra, lValorTotalCompra,
            lCodigoMercadoria, lQuantidadeMercadoria;
   //campos de texto
   private JTextField tCodigoMercadoria, tQuantidadeMercadoria;               
   //entrada de textos
   private JTextArea ePainel;
   //paineis
   private JPanel jCompra, jTAPainel, jMercadoria;
   //scroll
   private JScrollPane pScroll;
   //data
   private DateFormat dateFormat;
   private Date date;
   //mercadoriaGui
   private MercadoriaGUI me;
   //erro erroJTextField
   private String erroJTextField;
   //formata��o de double
   private DecimalFormat duas;
   //compra fechada
   private boolean compraFechada;
   //mercadoria adicionada
   private boolean mercadoriaAdicinada;
   
   /**
   * M�todo construtor
   **/
   public PontoDeVendaGUI()
   {  
      super("Mercado USJT - Ponto de Venda (PDV)");
      //instancia objeto border layout
      Container c = getContentPane();
      c.setLayout(new BorderLayout());
      
      //instancia objeto bot�o  
      bNovaCompra           = new JButton("Nova Compra");
      bCancelarNovaCompra   = new JButton("Cancelar Nova Compra");
      bFecharCompra         = new JButton("Fechar Compra");
      bFecharPDV            = new JButton("Fechar PDV");
      bAdicionaMercadoria   = new JButton("Adiciona Mercadoria");
      bCancelaMercadoria    = new JButton("Cancela Mercadoria");
      bExibirMercadoria     = new JButton("Exibir Mercadorias");
      bExibirTop10          = new JButton("Exibir TOP 10");
      
      //instancia objeto data
      dateFormat            = new SimpleDateFormat("dd/MM/yyyy");
      date                  = new Date(); 
      
      //instancia objeto texto         
      lTotalCompra          = new JLabel(" Total da compra:");
      lValorTotalCompra     = new JLabel("");
      lCodigoMercadoria     = new JLabel(" C�digo Mercadoria"); 
      lQuantidadeMercadoria = new JLabel(" Quandidade Mercadoria");
      
      //instancia objeto entradas de texto
      tCodigoMercadoria     = new JTextField(10);  
      tQuantidadeMercadoria = new JTextField(10);
      
      //instancia objeto entrada de textos
      ePainel               = new JTextArea(50, 5);
      pScroll               = new JScrollPane(ePainel);   
      ePainel.append("Mercado USJT - Caixa aberto - " + dateFormat.format(date));  
      ePainel.append("\n-------------------------------------------------------------------");          
               
      //instancia objeto paineis 
      jCompra               = new JPanel();
      jCompra.setLayout(new GridLayout(6 ,2 , 4, 4));
      
      jTAPainel             = new JPanel();
      jTAPainel.setLayout(new GridLayout(1, 1, 4, 4));
      
      jMercadoria           = new JPanel();
      jMercadoria.setLayout(new GridLayout(8, 1 , 4, 4)); 
      
      //instancia objeto PontoDeVenda 
      p = new PontoDeVenda();
      
      //instancia objeto mercadoriaGui
      me = new MercadoriaGUI();
      me.setVisible(false);
      me.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      
      //instancia objeto formata��o
      duas = new DecimalFormat("0.00");
      
      //adiciona elementos ao painel
      jCompra.add(bNovaCompra);
      jCompra.add(bCancelarNovaCompra);
      jCompra.add(bFecharCompra);
      jCompra.add(lTotalCompra);
      jCompra.add(lValorTotalCompra);
      jCompra.add(bFecharPDV);
      
      //adiciona elementos ao painel
      jTAPainel.add(pScroll);
      
      //adiciona elementos ao painel 
      jMercadoria.add(lCodigoMercadoria);
      jMercadoria.add(tCodigoMercadoria);
      jMercadoria.add(lQuantidadeMercadoria);
      jMercadoria.add(tQuantidadeMercadoria);
      jMercadoria.add(bAdicionaMercadoria);
      jMercadoria.add(bCancelaMercadoria);
      jMercadoria.add(bExibirMercadoria);
      jMercadoria.add(bExibirTop10);
      
      //adicona os paineis ao border layout
      c.add(BorderLayout.WEST, jCompra);
      c.add(BorderLayout.CENTER, jTAPainel);
      c.add(BorderLayout.EAST, jMercadoria);
      
      //coloca a��o nos bot�es
      bNovaCompra.addActionListener(this);
      bCancelarNovaCompra.addActionListener(this);
      bFecharCompra.addActionListener(this);
      bFecharPDV.addActionListener(this);
      bAdicionaMercadoria.addActionListener(this);
      bCancelaMercadoria.addActionListener(this);
      bExibirMercadoria.addActionListener(this);
      bExibirTop10.addActionListener(this);
      
      //desabilita edi��o campos de texto
      tCodigoMercadoria.setEditable(false);
      tQuantidadeMercadoria.setEditable(false);  
      
      //desabilita o clique de bot�es
      bAdicionaMercadoria.setEnabled(false);
      bCancelaMercadoria.setEnabled(false);
      bFecharCompra.setEnabled(false); 
      bCancelarNovaCompra.setEnabled(false);
      
      //desabilita a edi��o no JTextArea
      ePainel.setEditable(false);
      
      setSize( 720, 450 );
      setLocation( 350, 200 );
      setVisible( true );
      
      //inicializa true compra fechada
      compraFechada = true;
      
      //inicializa false mercadoria adicionada
      mercadoriaAdicinada = false;
   }
   
   public void actionPerformed(ActionEvent e){
      //a��o ao clicar no bot�o nova compra
      if(e.getSource() == bNovaCompra){
         //desabilita edi��o campos de texto
         tCodigoMercadoria.setEditable(true);
         tQuantidadeMercadoria.setEditable(true); 
         
         //desabilita o clique de bot�es
         bFecharPDV.setEnabled(true); 
         bFecharCompra.setEnabled(true); 
         bCancelarNovaCompra.setEnabled(true);
         bNovaCompra.setEnabled(false);
         bAdicionaMercadoria.setEnabled(true);
         bCancelaMercadoria.setEnabled(true);
         
         //limpa os campos de texto
         tCodigoMercadoria.setText("");
         tQuantidadeMercadoria.setText("");
         
         //atualiza o total da compra
         lValorTotalCompra.setText( " " );   
         
         //inicia o painel de nova compra
         ePainel.setText("");
         ePainel.append("Nova compra iniciada - " + dateFormat.format(date));
         ePainel.append("\n-------------------------------------------------------------------");         
         try
         {  
            //adiciona nova compra no banco          
            p.novaCompra();
            //atribui compra est� aberta
            setCompraFechada(false);
            //atribui mercadoria n�o adicionada
            setMercadoriaAdicinada(false);
         }
         catch(Exception f){
            f.printStackTrace();
         }
         
      }
      
      //a��o ao clicar no bot�o cancelar nova compra
      if(e.getSource() == bCancelarNovaCompra){
      
         boolean continuar = true;
         
         while (continuar)
         {    
            //... Define Array para os bot�es.
            String[] strOpcoesArray = {"Sim", "N�o"};
            
            //... Repeti��o definida pelo sentinela
            int resposta = JOptionPane.showOptionDialog(
               null                             // Centro da janela.
               , "Deseja realmente cancelar a Compra?"     // Messagem no corpo da caixa
               , "Escolha uma op��o"            // T�tulo da caixa
               , JOptionPane.YES_NO_OPTION      // Tipo de op��o
               , JOptionPane.QUESTION_MESSAGE   // Tipo da mensagem
               , null                           // �cone
               , strOpcoesArray                 // Array com texto dos bot�es
               , "Sim"                          // Bot�o padr�o
               );
            
            //... Uso de switch para determinar qual bot�o foi clicado
            switch (resposta) {         
               case 0:
                  //desabilita edi��o campos de texto
                  tCodigoMercadoria.setEditable(false);
                  tQuantidadeMercadoria.setEditable(false);
                  
                  //desabilita o clique de bot�es
                  bFecharCompra.setEnabled(false); 
                  bCancelarNovaCompra.setEnabled(false);
                  bNovaCompra.setEnabled(true);
                  bAdicionaMercadoria.setEnabled(false);
                  bCancelaMercadoria.setEnabled(false);
               
                  //limpa os campos de texto
                  tCodigoMercadoria.setText("");
                  tQuantidadeMercadoria.setText("");
                  
                  //limpa o JTextArea
                  ePainel.setText("");
                  
                  //acrescenta texto padr�o
                  ePainel.append("Mercado USJT - Caixa aberto - " + dateFormat.format(date));  
                  ePainel.append("\n-------------------------------------------------------------------");
                  
                  //atualiza o total da compra
                  lValorTotalCompra.setText( " " );   
                  
                  try
                  {       
                     //cancela compra no banco     
                     p.cancelaCompra();
                     //atribui compra fechada
                     setCompraFechada(true);
                     //atribui mercadoria n�o adicionada
                     setMercadoriaAdicinada(false);
                  }
                  catch(Exception f){
                     f.printStackTrace();
                  }
                  continuar = false;
                  break;
               
               case 1:
               case -1:
                  continuar = false;
                  break;
               
               default:
                  //... Algo est� errado se chegar aqui. Programa��o defensiva
                  JOptionPane.showMessageDialog(null, 
                     "Resposta inesperada " + resposta);
            }
         }                             
      }
      
      //a��o ao clicar no bot�o fechar compra
      if(e.getSource() == bFecharCompra){
         if( getMercadoriaAdicinada() )
         {
            boolean aprova =  p.cartaoAprovado();
            boolean continuar = true;
            
            while (continuar)
            {    
               //... Define Array para os bot�es.
               String[] strOpcoesArray = {"Sim", "N�o"};
               
               //... Repeti��o definida pelo sentinela
               int resposta = JOptionPane.showOptionDialog(
                  null                             // Centro da janela.
                  , "Deseja realmente fechar a Compra?"     // Messagem no corpo da caixa
                  , "Escolha uma op��o"            // T�tulo da caixa
                  , JOptionPane.YES_NO_OPTION      // Tipo de op��o
                  , JOptionPane.QUESTION_MESSAGE   // Tipo da mensagem
                  , null                           // �cone
                  , strOpcoesArray                 // Array com texto dos bot�es
                  , "Sim"                          // Bot�o padr�o
                  );
               
               //... Uso de switch para determinar qual bot�o foi clicado
               switch (resposta) {         
                  case 0:
                     //desabilita edi��o campos de texto
                     tCodigoMercadoria.setEditable(false);
                     tQuantidadeMercadoria.setEditable(false);
                     
                     //desabilita o clique de bot�es
                     bFecharCompra.setEnabled(false); 
                     bCancelarNovaCompra.setEnabled(false);                     
                     bAdicionaMercadoria.setEnabled(false);
                     bCancelaMercadoria.setEnabled(false);
                  
                     //limpa os campos de texto
                     tCodigoMercadoria.setText("");
                     tQuantidadeMercadoria.setText("");
                     
                     //inicializa o fechamento da compra                         
                     ePainel.append("\n\n-------------------------------------------------------------------");
                     ePainel.append("\nF E C H A M E N T O  D A  C O M P R A");
                     ePainel.append("\n-------------------------------------------------------------------");
                     ePainel.append("\nUNIDADES: " + p.qtdTotalDaCompra() + "  TOTAL R$ " + duas.format(p.totalDaCompra()) );
                     ePainel.append("\n-------------------------------------------------------------------");
                     
                     //solicita forma de pagamento
                     boolean continuarPagamento = true;
                     while (continuarPagamento)
                     {
                        //... Define Array para os bot�es.
                        String[] strOpcoesArrayPagamento = {"Cart�o", "Dinheiro"};
                           
                        //... Repeti��o definida pelo sentinela
                        int respostaPagamento = JOptionPane.showOptionDialog(
                           null                             // Centro da janela.
                           , "Qual op��o de pagamento?"     // Messagem no corpo da caixa
                           , "Escolha uma op��o"            // T�tulo da caixa
                           , JOptionPane.YES_NO_OPTION      // Tipo de op��o
                           , JOptionPane.QUESTION_MESSAGE   // Tipo da mensagem
                           , null                           // �cone
                           , strOpcoesArrayPagamento        // Array com texto dos bot�es
                           , "Cart�o"                       // Bot�o padr�o
                           );
                           
                        //... Uso de switch para determinar qual bot�o foi clicado
                        
                        //verifica qual op��o de pagamento
                        switch (respostaPagamento) 
                        {  
                           case 0:
                              try
                              {  
                                 //inclui compra no banco se a vari�vel 'aprova' for true
                                 p.fecharCompraCartao( aprova );  
                                 if( aprova )
                                 {
                                    ePainel.append("\n\n-------------------------------------------------------------------");
                                    ePainel.append("\nPagamento AUTORIZADO pela operadora");
                                    ePainel.append("\n-------------------------------------------------------------------");
                                    //atribui compra fechada
                                    setCompraFechada(true);
                                    //atribui mercadoria n�o adicionada
                                    setMercadoriaAdicinada(false);
                                    //bot�o nova compra ativa
                                    bNovaCompra.setEnabled(true);
                                 }
                                 else
                                 {
                                    bFecharCompra.setEnabled(true); 
                                    bCancelarNovaCompra.setEnabled(true);
                                    ePainel.append("\n\n-------------------------------------------------------------------");
                                    ePainel.append("\nPagamento N�O AUTORIZADO pela operadora");
                                    ePainel.append("\n-------------------------------------------------------------------");
                                 }
                              }
                              catch(Exception f){
                                 JOptionPane.showMessageDialog(null,
                                                                     f.getMessage(),
                                                                     "Erro!",
                                                                     JOptionPane.ERROR_MESSAGE);
                              }
                              continuarPagamento = false;
                              break;
                           
                           case 1:
                              try
                              {
                                 //inclui compra no banco
                                 p.fecharCompraDinheiro();
                                 ePainel.append("\n\n-------------------------------------------------------------------");
                                 ePainel.append("\nT R O C O : " + p.getTroco() );
                                 ePainel.append("\n-------------------------------------------------------------------");
                                 //atribui compra fechada
                                 setCompraFechada(true);
                                 //bot�o nova compra ativa
                                 bNovaCompra.setEnabled(true);
                              }
                              catch(Exception f){
                                 JOptionPane.showMessageDialog(null,
                                                                     f.getMessage(),
                                                                     "Erro!",
                                                                     JOptionPane.ERROR_MESSAGE);
                              }                          
                              continuarPagamento = false;
                              break;
                           
                           case -1:
                              continuarPagamento = false;
                              break;
                           
                           default:
                              //... Algo est� errado se chegar aqui. Programa��o defensiva
                              JOptionPane.showMessageDialog(null, 
                                 "Resposta inesperada " + resposta);
                                 }
                     }
                     
                     continuar = false;
                     break;
                  
                  case 1:
                  case -1:
                     continuar = false;
                     break;
                  
                  default:
                     //... Algo est� errado se chegar aqui. Programa��o defensiva
                     JOptionPane.showMessageDialog(null, 
                        "Resposta inesperada " + resposta);
               }
            }  
         }else
         {
            JOptionPane.showMessageDialog(null, "Insira uma mercadoria para fechar a compra!", "Erro!", JOptionPane.ERROR_MESSAGE);
         }        
      }
      
      
      //a��o ao clicar no bot�o Fechar PDV
      if(e.getSource() == bFecharPDV){
         if( getCompraFechada() )
         {
            System.exit(0);
         }else
         {
            boolean continuar = true;
            
            while (continuar)
            {    
               //... Define Array para os bot�es.
               String[] strOpcoesArray = {"Sim", "N�o"};
               
               //... Repeti��o definida pelo sentinela
               int resposta = JOptionPane.showOptionDialog(
                  null                             // Centro da janela.
                  , "Deseja realmente cancelar a Compra?"     // Messagem no corpo da caixa
                  , "Escolha uma op��o"            // T�tulo da caixa
                  , JOptionPane.YES_NO_OPTION      // Tipo de op��o
                  , JOptionPane.QUESTION_MESSAGE   // Tipo da mensagem
                  , null                           // �cone
                  , strOpcoesArray                 // Array com texto dos bot�es
                  , "Sim"                          // Bot�o padr�o
                  );
               
               //... Uso de switch para determinar qual bot�o foi clicado
               switch (resposta) {         
                  case 0:
                     //cancela todas as compras atuais
                     p.cancelaCompra();
                     //fecha o sistema
                     System.exit(0);                     
                     continuar = false;
                     break;
                  
                  case 1:
                  case -1:
                     continuar = false;
                     break;
                  
                  default:
                     //... Algo est� errado se chegar aqui. Programa��o defensiva
                     JOptionPane.showMessageDialog(null, 
                        "Resposta inesperada " + resposta);
               }
            }                           
         }         
      }
      
      //a��o ao clicar no bot�o adiciona mercadoria
      if(e.getSource() == bAdicionaMercadoria){
         if( validaJTextField() )
         {
            int idMercadoria = Integer.parseInt(tCodigoMercadoria.getText());
            int quantidade = Integer.parseInt(tQuantidadeMercadoria.getText());
            
            try
            { 
               //insere no banco nova mercadoria           
               p.novoItemCompra(idMercadoria,quantidade);
               //insere no painel nova mercadoria  
               ePainel.append(p.getItemCompraById(idMercadoria, quantidade));
               //atualiza o total da compra
               lValorTotalCompra.setText( " R$ " + duas.format(p.totalDaCompra()) );
               //atribui mercadoria n�o adicionada
               setMercadoriaAdicinada(true);
            }
            catch(Exception f){
               JOptionPane.showMessageDialog(null,
                                             f.getMessage(),
                                             "Erro!",
                                             JOptionPane.ERROR_MESSAGE);
            }
         }
         else{
            JOptionPane.showMessageDialog(null, erroJTextField);
         }
      }
      
      //a��o ao clicar no bot�o cancela mercadoria
      if(e.getSource() == bCancelaMercadoria){        
         try
         {  
            ePainel.append("\n\n-------------------------------------------------------------------");
            ePainel.append("\nC A N C E L A M E N T O: ");
            ePainel.append("\n-------------------------------------------------------------------");
            //acessa ultima mercadoria adicionada para o atual id de compra
            ePainel.append("\n" + p.acessaUltimoItemCompra() + "\n\n");

            //exclui do banco de dados          
            p.excluiItemCompra();
            //atualiza o total da compra
            lValorTotalCompra.setText( " R$ " + duas.format(p.totalDaCompra()) );
         }
         catch(Exception f){
            f.printStackTrace();
         }
      }
      
      //a��o ao clicar no bot�o exibir mercadoria
      if(e.getSource() == bExibirMercadoria){
         if(me.isVisible() == false)
         {
            me.setVisible(true);
            bExibirMercadoria.setText("Esconder Mercadorias");
         }
         else
         {
            me.setVisible(false);
            bExibirMercadoria.setText("Exibir Mercadorias");
         }
      }
      if(e.getSource() == bExibirTop10){  
         JOptionPane.showMessageDialog(null, p.get10MaisVendidas(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
      }                         
   }
   
   //valida��o entrada de dados em C�digo Mercadoria e Quantidade
   public boolean validaJTextField()
   {  
      boolean val = true;
      
      if(tCodigoMercadoria.getText() == null)
      {
         erroJTextField = "O campo 'C�digo Mercadoria' n�o pode ser nulo!" +                        
                          "\n" ;
         val = false;
      }
      else if( tCodigoMercadoria.getText().trim().equals("") )
      {
         erroJTextField = "O campo 'C�digo Mercadoria' n�o pode ser vazio!" +                        
                          "\n" ;
         val = false;                             
      }
      else if( tQuantidadeMercadoria.getText() == null)
      {
         erroJTextField = "O campo 'Quantidade Mercadoria' n�o pode ser nulo!" +                        
                          "\n" ;      
         val = false;                          
      }
      else if( tQuantidadeMercadoria.getText().trim().equals(""))
      {
         erroJTextField = "O campo 'Quantidade Mercadoria' n�o pode ser vazio!" +                        
                          "\n" ;  
         val = false;                              
      }      
      return val;
   }
   
   //modificador compra fechada
   public void setCompraFechada( boolean compraFechada )
   {
      this.compraFechada = compraFechada;
   }
   
   //acesso compra fechada
   public boolean getCompraFechada()
   {
      return compraFechada;
   }      
   
   //modificador mercadoria fechada
   public void setMercadoriaAdicinada( boolean mercadoriaAdicinada )
   {
      this.mercadoriaAdicinada = mercadoriaAdicinada;
   }
   
   //acesso mercadoria fechada
   public boolean getMercadoriaAdicinada()
   {
      return mercadoriaAdicinada;
   }
   
}//fim da classe