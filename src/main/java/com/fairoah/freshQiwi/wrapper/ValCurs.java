package com.fairoah.freshQiwi.wrapper;

import java.util.List;

import com.fairoah.freshQiwi.model.Valute;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ValCurs")
public class ValCurs {
	private String date;
    private String name;
    private List<Valute> valutes;

    @XmlAttribute(name = "Date")
    public void setDate(String date) {
        this.date = date;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Valute")
    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }

    // Getters for the fields
    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<Valute> getValutes() {
        return valutes;
    }

    
}
