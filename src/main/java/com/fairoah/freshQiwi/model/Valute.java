package com.fairoah.freshQiwi.model;

import java.util.Iterator;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Valute")
public class Valute {
    private String id;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;


    @XmlAttribute(name = "ID")
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "NumCode")
    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    @XmlElement(name = "CharCode")
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    public void setValue(String value) {
        this.value = value;
    }

	public String getId() {
		return id;
	}

	public String getNumCode() {
		return numCode;
	}

	public String getCharCode() {
		return charCode;
	}

	public int getNominal() {
		return nominal;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
    
    
}