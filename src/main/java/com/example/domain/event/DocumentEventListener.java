package com.example.domain.event;

import com.example.domain.entity.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentEventListener {

  @EventListener
  public void onDocumentCreated(Document.OnCreatedEvent event) {

    Document document = event.getDocument();

    log.info("document (id={}, name={}) created !", document.getId(), document.getName());
  }
}
