package com.assemblyvoting.api.vote;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.assemblyvoting.api.agenda.AgendaEntity;
import com.assemblyvoting.api.associate.AssociateEntity;

@Entity
@Table(name = "vote")
public class VoteEntity {
	private int id;
	private AssociateEntity associate;
	private AgendaEntity agenda;
	private Date creationDatetime;
	private VoteEnum choise;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name = "cpf", referencedColumnName = "cpf")
	public AssociateEntity getAssociate() {
		return associate;
	}
	public void setAssociate(AssociateEntity associate) {
		this.associate = associate;
	}
	
	@OneToOne
	@JoinColumn(name = "subject", referencedColumnName = "subject")	
	public AgendaEntity getAgenda() {
		return agenda;
	}
	public void setAgenda(AgendaEntity agenda) {
		this.agenda = agenda;
	}
	
	@Column(name = "creation_datetime")
	public Date getCreationDatetime() {
		return creationDatetime;
	}
	public void setCreationDatetime(Date creationDatetime) {
		this.creationDatetime = creationDatetime;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)	
	public VoteEnum getChoise() {
		return choise;
	}
	public void setChoise(VoteEnum choise) {
		this.choise = choise;
	}	
}
