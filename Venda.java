import java.sql.Date;
// import java.util.ArrayList;
import java.util.Calendar;

public class Venda {
    private int cod_venda = 1;
    private java.sql.Date data_venda;
    private Double desconto = 0.;
    private Double total_venda;
    private String forma_pagamento;
    private VendaDAO vendaDAO;

    // CONSTRUTORES
    public Venda(){

    }

    public Venda(String forma_pagamento){
        vendaDAO = new VendaDAO();
        this.cod_venda = vendaDAO.codigoVenda(cod_venda);
        System.out.println(this.cod_venda);
        this.forma_pagamento = forma_pagamento; 
        this.data_venda = new Date(Calendar.getInstance().getTimeInMillis());
    }

    // GETS

    public int getCod_venda() {
        return cod_venda;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public Double getDesconto() {
        return desconto;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public Double getTotal_venda() {
        return total_venda;
    }
    
    // SETS

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public void setData_venda(Date data) {
        this.data_venda = data;
    }

    // public void setData_venda(int ano, int mes, int dia) {
    //     if(ano <= 0 || mes <= 0 || dia <= 0){
    //         throw new IllegalArgumentException("Valor do Dia, Mês ou Ano negativos ou igual a 0!");
    //     }
    //     Calendar data = Calendar.getInstance();
    //     data.set(ano, mes-1, dia);
    //     this.data_venda = new java.sql.Date(data.getTimeInMillis());
    // }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public void setTotal_venda(Double total_venda) {
        this.total_venda = total_venda;
    }


}
