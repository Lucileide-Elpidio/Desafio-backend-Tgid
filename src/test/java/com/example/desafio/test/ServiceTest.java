import com.example.desafio.cliente.Cliente;
import com.example.desafio.empresa.Empresa;
import com.example.desafio.empresa.TaxaSistema;
import com.example.desafio.servico.TransacaoService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Esta classe contém testes unitários para a classe TransacaoService.
 */
public class TransacaoServiceTest {

    /**
     * Testa o método deposito da classe TransacaoService.
     */
    @Test
    public void testDeposito() {
        // Cria um cliente com saldo inicial de 1000.0
        Cliente cliente = new Cliente();
        cliente.setSaldo(1000.0);

        // Cria uma empresa com saldo inicial de 5000.0
        Empresa empresa = new Empresa();
        empresa.setSaldo(5000.0);

        // Adiciona uma taxa à empresa
        TaxaSistema taxa = new TaxaSistema();
        taxa.setNome("Taxa 1");
        taxa.setValor(50.0);
        empresa.getTaxas().add(taxa);

        // Realiza um depósito de 200.0
        TransacaoService.deposito(cliente, empresa, 200.0);

        // Verifica se o saldo da empresa foi atualizado corretamente
        assertEquals(5200.0, empresa.getSaldo());

        // Verifica se o saldo do cliente foi atualizado corretamente
        assertEquals(800.0, cliente.getSaldo());
    }
}

