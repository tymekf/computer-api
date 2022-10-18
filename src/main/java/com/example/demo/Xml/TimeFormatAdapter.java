package com.example.demo.Xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeFormatAdapter extends XmlAdapter <String, LocalDate> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;

    @Override
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v, dateTimeFormatter);
    }

    @Override
    public String marshal(LocalDate v) {
        return dateTimeFormatter.format(v);
    }
}