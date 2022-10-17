package com.example.demo.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement (name = "faktura")
public class Computers {

    Collection<Computer> computers;

    public Collection<Computer> getComputers() {
        return computers;
    }

    @XmlElement(name = "komputer")
    public void setComputers(Collection<Computer> computers) {
        this.computers = computers;
    }

    public Computers(Collection<Computer> computers) {
        this.computers = computers;
    }

    public Computers() {
    }

    @Override
    public String toString() {
        return "Computers{" +
                "computers=" + computers +
                '}';
    }
}