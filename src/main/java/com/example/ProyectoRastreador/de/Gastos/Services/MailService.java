package com.example.ProyectoRastreador.de.Gastos.Services;

import com.example.ProyectoRastreador.de.Gastos.Enums.CategoriaGasto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    //Email sin plantilla html
    /*public void sendMail(String email, String descripcion, CategoriaGasto categoriaGasto){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(email);
            mailMessage.setSubject("Asunto Prueba");
            mailMessage.setText("Has creado un nuevo gasto que tiene como descripcipn: " + descripcion + " Y su categoria es: " + categoriaGasto.name());
            this.javaMailSender.send(mailMessage);
            logger.info("Email enviado con exito....");

        } catch (Exception e) {
            logger.error("Error al enviar el correo: " + e.getMessage());

        }


    }*/


    public void emailSendForTemplate(String email, String descripcion, CategoriaGasto categoriaGasto) throws MessagingException {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context();
        context.setVariable("categoria", categoriaGasto);
        context.setVariable("descripcion", descripcion);

        String contenidoTemplate = this.templateEngine.process("email-template", context);

        helper.setTo(email);
        helper.setSubject("Creacion de nuevo gasto");
        helper.setText(contenidoTemplate, true);

        this.javaMailSender.send(mimeMessage);
    }

}
