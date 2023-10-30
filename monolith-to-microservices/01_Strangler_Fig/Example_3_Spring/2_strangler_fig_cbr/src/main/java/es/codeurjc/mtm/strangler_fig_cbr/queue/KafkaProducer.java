package es.codeurjc.mtm.strangler_fig_cbr.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Value(value = "${kafka.topic.producer.payroll.name}")
  private String payrollTopic;

  @Value(value = "${kafka.topic.producer.invoicing.name}")
  private String invoicingTopic;

  public void sendPayrollMessage(String message) {
    sendMessage(message, this.payrollTopic);
  }

  public void sendInvoicingMessage(String message) {
    sendMessage(message, this.invoicingTopic);
  }

  public void sendMessage(String message, String topic){
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate
        .send(topic, message);
    future.whenComplete(new BiConsumer<>() {
      @Override
      public void accept(SendResult<String, String> stringStringSendResult, Throwable throwable) {
        if (throwable != null) {
          log.error("Something went wrong with the message {} ", message);
        } else {
          log.debug("Message {} has been sent ", message);
        }
      }

      @Override
      public BiConsumer<SendResult<String, String>, Throwable> andThen(BiConsumer<? super SendResult<String, String>, ? super Throwable> after) {
        return BiConsumer.super.andThen(after);
      }
    });
  }
}