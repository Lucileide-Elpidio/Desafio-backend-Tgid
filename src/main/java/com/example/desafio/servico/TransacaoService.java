package com.example.desafio.servico;

import com.example.desafio.cliente.Cliente;
import com.example.desafio.empresa.Empresa;
import com.example.desafio.empresa.TaxaSistema;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * A classe TransacaoService fornece métodos para realizar transações entre um cliente
 * e uma empresa, incluindo depósitos e saques, bem como o envio de callbacks e notificações.
 */
public class TransacaoService {
    private static final String WEBHOOK_URL = "https://webhook.site/97af6911-e3c7-4cc7-a1ef-8cfec049b407";

    /**
     * Realiza um depósito na conta do cliente e atualiza o saldo da empresa.
     *
     * @param cliente O cliente que está realizando o depósito.
     * @param empresa A empresa que recebe o depósito.
     * @param valor O valor a ser depositado.
     */
    public static void deposito(Cliente cliente, Empresa empresa, double valor) {
        double valorComTaxa = valor;
        for (TaxaSistema taxa : empresa.getTaxas()) {
            valorComTaxa -= taxa.getValor();
        }
        empresa.setSaldo(empresa.getSaldo() + valorComTaxa);
        cliente.setSaldo(cliente.getSaldo() - valor);
        enviarCallback(empresa, "Transação de depósito realizada");
        enviarNotificacao(cliente, "Depósito realizado com sucesso");
    }

    /**
     * Realiza um saque na conta do cliente e atualiza o saldo da empresa, se o saldo do cliente for suficiente.
     *
     * @param cliente O cliente que está realizando o saque.
     * @param empresa A empresa que processa o saque.
     * @param valor O valor a ser sacado.
     * @throws SaldoInsuficienteException Se o saldo do cliente for insuficiente para o saque.
     */
    public static void saque(Cliente cliente, Empresa empresa, double valor) throws SaldoInsuficienteException {
        if (cliente.getSaldo() >= valor) {
            double valorComTaxa = valor;
            for (TaxaSistema taxa : empresa.getTaxas()) {
                valorComTaxa -= taxa.getValor();
            }
            empresa.setSaldo(empresa.getSaldo() - valorComTaxa);
            cliente.setSaldo(cliente.getSaldo() - valor);
            enviarCallback(empresa, "Transação de saque realizada");
            enviarNotificacao(cliente, "Saque realizado com sucesso");
        } else {
            enviarNotificacao(cliente, "Saldo insuficiente para o saque");
        }
    }

    /**
     * Envia um callback para a empresa informando sobre uma transação.
     *
     * @param empresa A empresa que receberá o callback.
     * @param mensagem A mensagem a ser incluída no callback.
     */
    private static void enviarCallback(Empresa empresa, String mensagem) {
        try {
            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject callbackData = new JSONObject();
            callbackData.put("empresa", empresa.getCnpj());
            callbackData.put("mensagem", mensagem);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = callbackData.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Callback para Empresa - Código de Resposta: " + responseCode);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Envia uma notificação por e-mail para o cliente informando sobre uma transação.
     *
     * @param cliente O cliente que receberá a notificação.
     * @param mensagem A mensagem a ser incluída no e-mail.
     */
    private static void enviarNotificacao(Cliente cliente, String mensagem) {
        final String username = "seu_email@gmail.com";
        final String password = "sua_senha";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("clientebancodev@gmail.com"));
            message.setSubject("Banco Dev");
            message.setText(mensagem);

            Transport.send(message);

            System.out.println("E-mail enviado com sucesso.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

