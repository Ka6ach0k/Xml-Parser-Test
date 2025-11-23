package org.example.xml.parser.jaxb.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.xml.parser.jaxb.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


@Getter
@Setter
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    private String id;

    private String author;
    private String title;
    private String genre;
    private double price;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate publish_date;
    private String description;
}