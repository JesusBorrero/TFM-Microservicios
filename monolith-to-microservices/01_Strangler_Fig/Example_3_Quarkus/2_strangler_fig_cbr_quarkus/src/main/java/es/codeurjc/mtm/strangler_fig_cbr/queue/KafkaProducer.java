package es.codeurjc.mtm.strangler_fig_cbr.queue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

  @Inject
  @Channel("payroll-outgoing")
  Emitter<String> payrollEmitter;

  @Inject
  @Channel("invoicing-outgoing")
  Emitter<String> invoicingEmitter;

  public void sendPayrollMessage(String message) {
    sendMessage(message, this.payrollEmitter);
  }

  public void sendInvoicingMessage(String message) {
    sendMessage(message, this.invoicingEmitter);
  }

  public void sendMessage(String message, Emitter<String> emitter){
    CompletionStage<Void> future = emitter.send(message);

    future.whenComplete(new BiConsumer<>() {

      @Override
      public void accept(Void unused, Throwable throwable) {
        if (throwable != null) {
          log.error("Something went wrong with the message {} ", message);
        } else {
          log.debug("Message {} has been sent ", message);
        }
      }

      @Override
      public BiConsumer<Void, Throwable> andThen(BiConsumer<? super Void, ? super Throwable> after) {
        return BiConsumer.super.andThen(after);
      }
    });
  }
}