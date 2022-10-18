package com.example.demo.Xml;

import com.example.demo.ComputerCreator;
import com.example.demo.Models.Computer;
import com.example.demo.Computers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlModifier {

    public void generateXml() {
        ComputerCreator computerCreator = new ComputerCreator();
        List<Computer> computerList = new ArrayList<>(computerCreator.createComputers());
        Computers computers = new Computers(computerList);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Computers.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            File file = new File("C://Users/Tymek/Downloads/computer-api/computers.xml");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(computers, file);
            marshaller.marshal(computers, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}