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

  public Document saveOld() {

    Document document = repository.save(Document.createOld("xxx"));

    eventPublisher.publishEvent(new Document.OnCreatedEvent(document));

    return document;
  }

  public Document saveNew() {

    return repository.save(Document.createNew("yyy"));
  }
}
