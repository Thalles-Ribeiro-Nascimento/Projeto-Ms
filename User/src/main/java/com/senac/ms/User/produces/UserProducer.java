package com.senac.ms.User.produces;

import com.senac.ms.User.dtos.EmailDto;
import com.senac.ms.User.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    @Autowired
    RabbitTemplate rmqTemplate;

    @Value(value = "{broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userModel.getName() + ", seja bem-vindo(a)!\nAgradecemos o seu cadastro!");

        rmqTemplate.convertAndSend("", routingKey, emailDto);
    }
}
