package com.example.demo.Models;

import com.example.demo.NbpApi;
import com.example.demo.Xml.TimeFormatAdapter;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "komputer")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "faktura")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int id;

    @Column(name = "nazwa")
    @XmlElement(name = "nazwa")
    private String name;

    @Column(name = "data_ksiegowania")
    @XmlElement(name = "data_ksiegowania")
    @XmlJavaTypeAdapter(TimeFormatAdapter.class)
    private LocalDate postingDate;

    @Column(name = "koszt_USD")
    @XmlElement(name = "koszt_USD")
    private double priceInUsd;

    @Column(name = "koszt_PLN")
    @XmlElement(name = "koszt_PLN")
    private double priceInPln;

    public Computer() {
    }

    public Computer(String name, LocalDate postingDate, double priceInUsd) {
        this.name = name;
        this.postingDate = postingDate;
        this.priceInUsd = priceInUsd;
        setPriceInPln();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public double getPriceInUsd() {
        return priceInUsd;
    }

    public void setPriceInUsd(double priceInUsd) {
        this.priceInUsd = priceInUsd;
    }

    public double getPriceInPln() {
        return priceInPln;
    }

    public void setPriceInPln() {
        NbpApi nbpApi = new NbpApi();
        nbpApi.fetchDataFromNbpApi(getPostingDate());
        priceInPln = Math.round (priceInUsd * nbpApi.getDollarRateForThatDay() * 100.0)/ 100.0;
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", purchase date: " + postingDate +
                ", USD cost: " + priceInUsd +
                ", PLN cost: " + priceInPln;
    }
}