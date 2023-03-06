package com.example.producer.service;


import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerService {

  @Value("${service.kafka-topic}")
  private String kafkaTopic;

  private final KafkaTemplate<String, String> kafkaTemplate;

  private final Random random = new Random();

  @Scheduled(cron = "${service.send-cron}")
  public void sendRandomNumberToKafka() {

    int randomNumber = random.nextInt(10) + 1;
    kafkaTemplate.send(kafkaTopic, "randomNumber", String.valueOf(randomNumber));
    log.info("Sent message with random number: {}", randomNumber);
  }
}
