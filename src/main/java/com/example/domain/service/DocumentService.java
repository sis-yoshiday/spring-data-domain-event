package com.example.domain.service;

import com.example.domain.entity.Document;
import com.example.domain.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentService {

  private final DocumentRepository repository;

  private final ApplicationEventPublisher eventPublisher;

  public Document saveOld(String name) {

    Document document = repository.save(Document.createOld(name));

    eventPublisher.publishEvent(new Document.OnCreated(document));

    return document;
  }

  public Document saveNew(String name) {

    return repository.save(Document.createNew(name));
  }
}
