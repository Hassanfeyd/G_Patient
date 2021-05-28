package org.sid.Entities;

import java.util.Date;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import lombok.Data;

@Data
@Entity
public class Patient {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
   @NotNull
	@Size(min=2, max=15)
	private String name;
	@javax.persistence.Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date dataNaissance ;
	private boolean malade; 
	public Patient( String nom, Date dataNaissance, boolean malade, int score) {
		super();
		
		this.name = nom;
		this.dataNaissance = dataNaissance;
		this.malade = malade;
		this.score = score;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String nom) {
		this.name = nom;
	}
	public Date getDataNaissance() {
		return dataNaissance;
	}
	public void setDataNaissance(Date dataNaissance) {
		this.dataNaissance = dataNaissance;
	}
	public boolean isMalade() {
		return malade;
	}
	public void setMalade(boolean malade) {
		this.malade = malade;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@DecimalMin(value = "4")
	private int score;
	
	

}
