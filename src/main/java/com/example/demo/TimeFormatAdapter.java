package com.example.demo;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeFormatAdapter extends XmlAdapter <String, LocalDate> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        LocalDate parse = LocalDate.parse(v, dateTimeFormatter);
        return parse;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return dateTimeFormatter.format(v);
    }
}
