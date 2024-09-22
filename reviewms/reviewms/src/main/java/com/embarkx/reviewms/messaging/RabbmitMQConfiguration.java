package com.embarkx.reviewms.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbmitMQConfiguration {

	@Bean
	public Queue companyRating() {
		return new Queue("companyRatingQueue");
	}

	@Bean
	public MessageConverter jsonMsgConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory conneciton) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(conneciton);
		rabbitTemplate.setMessageConverter(jsonMsgConverter());
		return rabbitTemplate;

	}
}
