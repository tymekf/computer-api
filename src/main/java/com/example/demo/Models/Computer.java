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

    public static final String name = "nazwa";
    public static final String date = "data_ksiegowania";
    public static final String plnPrice = "koszt_PLN";
    public static final String usdPrice = "koszt_USD";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private int id;

    @Column(name = name)
    @XmlElement(name = name)
    private String computerName;

    @Column(name = date)
    @XmlElement(name = date)
    @XmlJavaTypeAdapter(TimeFormatAdapter.class)
    private LocalDate postingDate;

    @Column(name = usdPrice)
    @XmlElement(name = usdPrice)
    private double priceInUsd;

    @Column(name = plnPrice)
    @XmlElement(name = plnPrice)
    private double priceInPln;

    public Computer() {
    }

    public Computer(String name, LocalDate postingDate, double priceInUsd) {
        this.computerName = name;
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

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
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
        return computerName + '\'' +
                ", purchase date: " + postingDate +
                ", USD cost: " + priceInUsd +
                ", PLN cost: " + priceInPln;
    }
}