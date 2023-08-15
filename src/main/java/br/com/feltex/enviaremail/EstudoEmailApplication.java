package br.com.feltex.enviaremail;

import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.feltex.enviaremail.service.SendEmailService;
import br.com.feltex.enviaremail.service.SendEmailServiceImpl;

@SpringBootApplication
public class EstudoEmailApplication implements CommandLineRunner {

    private final SendEmailService sendEmailService;

    public EstudoEmailApplication(SendEmailServiceImpl enviaEmailService) {
        this.sendEmailService = enviaEmailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(EstudoEmailApplication.class, args);
    }

    @Override
    public void run(String... args) throws MessagingException {
        sendEmailService.sendSimpleEmail("geraldo.gja@gmail.com", "Serviço de Email",
                "Conteúdo do email aqui!");

        HashMap<String, String> arquivosAnexo = new HashMap<>(); 
        arquivosAnexo.put("corrida.jpeg", "arquivos/corrida.jpeg");
        arquivosAnexo.put("basket.jpeg", "arquivos/basket.jpeg");
        
        sendEmailService.sendEmailWithAttachments("geraldo.gja@gmail.com", "Serviço de Email com Anexo",
                "Email com Anexo", arquivosAnexo);
    }
}
