package com.example.domain.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "document")
public class Document extends AbstractAggregateRoot implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  /* old */

  public static Document createOld(String name) {

    Document document = new Document();
    document.name = name;

    return document;
  }

  /* new */

  public static Document createNew(String name) {

    Document document = new Document();
    document.name = name;

    document.registerEvent(new OnCreated(document));

    return document;
  }

  @RequiredArgsConstructor
  @Getter
  public static class OnCreated {

    private final Document document;
  }
}
