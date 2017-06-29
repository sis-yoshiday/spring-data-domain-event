package com.example.domain.service;

import com.example.domain.entity.Document;
import com.example.domain.event.DocumentEventListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentServiceTest {

  @Autowired
  DocumentService target;

  @MockBean
  DocumentEventListener eventListener;

  @Captor
  ArgumentCaptor<Document.OnCreated> eventCaptor;

  @Test
  public void testOld() {

    Document actual = target.saveOld("xxx");

    verify(eventListener, times(1)).onCreated(eventCaptor.capture());

    assertThat(eventCaptor.getValue().getDocument().getId(), is(actual.getId()));
    assertThat(eventCaptor.getValue().getDocument().getName(), is("xxx"));
  }

  @Test
  public void testNew() {

    Document actual = target.saveNew("yyy");

    verify(eventListener, times(1)).onCreated(eventCaptor.capture());

    assertThat(eventCaptor.getValue().getDocument().getId(), is(actual.getId()));
    assertThat(eventCaptor.getValue().getDocument().getName(), is("yyy"));
  }
}
