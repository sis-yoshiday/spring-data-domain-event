package com.example.domain.service;

import com.example.domain.entity.Document;
import com.example.domain.event.DocumentEventListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {

  @Autowired
  DocumentService target;

  @SpyBean
  DocumentEventListener eventListener;

  @Captor
  ArgumentCaptor<Document.OnCreatedEvent> eventCaptor;

  @Test
  public void testOld() {

    target.saveOld();

    Mockito
        .verify(eventListener, Mockito.times(1))
        .onDocumentCreated(eventCaptor.capture());

    assertThat(eventCaptor.getValue().getDocument().getId(), is(notNullValue()));
    assertThat(eventCaptor.getValue().getDocument().getName(), is("xxx"));
  }

  @Test
  public void testNew() {

    target.saveNew();

    Mockito
        .verify(eventListener, Mockito.times(1))
        .onDocumentCreated(eventCaptor.capture());

    assertThat(eventCaptor.getValue().getDocument().getId(), is(notNullValue()));
    assertThat(eventCaptor.getValue().getDocument().getName(), is("yyy"));
  }
}
