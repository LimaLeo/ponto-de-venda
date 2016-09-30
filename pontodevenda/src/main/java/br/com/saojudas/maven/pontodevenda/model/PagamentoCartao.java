package br.com.saojudas.maven.pontodevenda.model;
import java.util.Random;

public class PagamentoCartao extends Pagamento
{
   private long numeroCartao;
   private String validadeCartao;
   
   //construtor sem par�metro
   public PagamentoCartao()
   {

   }
   
   //construtor com par�metro
   public PagamentoCartao(double valor, long numeroCartao, String validadeCartao)
   {
      super(valor);
      setNumeroCartao(numeroCartao);
      setValidadeCartao(validadeCartao);
   }
   
   //sobrecarga construtor com par�metro
   public PagamentoCartao(double valor)
   {
      super(valor);
   }
   
   //modificador n�mero do cart�o
   public void setNumeroCartao(long numeroCartao)
   {  
      String sNumeroCartao = Long.toString(numeroCartao);
      
      if(numeroCartao <= 0)
      {
         throw new RuntimeException("O 'N�mero do Cart�o' n�o pode ser menor ou igual a zero!'");
      }else if(sNumeroCartao.length() > 16 || sNumeroCartao.length() < 16)
      {
         throw new RuntimeException("O 'N�mero do Cart�o' deve ter 16 digitos!'");
      }else
      {
         this.numeroCartao = numeroCartao;
      }
   }
   
   //modificador validade do cart�o
   public void setValidadeCartao(String validadeCartao)
   {
      if( validadeCartao == null )
      {
         throw new RuntimeException("Erro! Validade do Cart�o n�o pode ser nulo!");
      }else if( validadeCartao.trim().equals(""))
      {
         throw new RuntimeException("Erro! Validade do Cart�o n�o pode estar vazio");
      }else
      {
         this.validadeCartao = validadeCartao.toUpperCase().trim();
      }   
   }
   
   //acesso n�mero do cart�o
   public long getNumeroCartao()
   {
      return numeroCartao;
   }
   
   //acesso validade do cart�o
   public String getValidadeCartao()
   {
      return validadeCartao;
   }
   
   //verifica se o cart�o foi aprovado
   public boolean cartaoAprovado()
   {  
      Random r = new Random();      
      return r.nextBoolean();
   }
}