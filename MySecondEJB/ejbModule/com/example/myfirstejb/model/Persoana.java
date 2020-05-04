package com.example.myfirstejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the persoana database table.
 * 
 */
@Entity
@NamedQuery(name = "Persoana.findAll", query = "SELECT p FROM Persoana p")
public class Persoana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nume;

	public Persoana(String nume) {
		this.nume = nume;
	}

	public Persoana() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

}