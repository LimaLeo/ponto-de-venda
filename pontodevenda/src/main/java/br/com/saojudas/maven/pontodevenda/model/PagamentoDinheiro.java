package br.com.saojudas.maven.pontodevenda.model;
import java.text.DecimalFormat;

public class PagamentoDinheiro extends Pagamento
{
   private double valorRecebido;
   
   //construtor com parametro
   public PagamentoDinheiro( double valor, double valorRecebido )
   {  
      super(valor);
      setValorRecebido(valorRecebido);
   }
   
   //sobrecarga construtor com parametro
   public PagamentoDinheiro( double valor)
   {  
      super(valor);
   }
   
   //modificador valor recebido
   public void setValorRecebido( double valorRecebido )
   {
      if(valorRecebido < valor)
      {
         throw new RuntimeException("Informe um valor v�lido");
      }else if( valorRecebido <= 0.0)
      {
         throw new RuntimeException("O 'Valor recebido' n�o pode ser manor ou igual a zero!");
      }else
      {
         this.valorRecebido = valorRecebido;
      }
   }
   
   //acesso valor recebido
   public double getValorRecebido()
   {
      return valorRecebido;
   }
   
   //acesso ao troco
   public String getTroco()
   {
      DecimalFormat duas = new DecimalFormat("0.00");
      double troco = getValorRecebido() - getValor() ;
      return " R$ " + duas.format(troco);
   }
}