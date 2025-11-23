package org.example.xml.parser.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String date) throws Exception {
        if(date == null || date.trim().isEmpty())
            return null;
        return LocalDate.parse(date.trim(), DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        if (date == null)
            return null;
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
