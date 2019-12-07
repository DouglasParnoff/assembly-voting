package com.assemblyvoting.api.associate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.assemblyvoting.api.vote.VoteEntity;

@Entity
@Table(name = "associate")
public class AssociateEntity {
	private String cpf;
	private String name;
	private VoteEntity vote;

	@Id
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne
	public VoteEntity getVote() {
		return vote;
	}

	public void setVote(VoteEntity vote) {
		this.vote = vote;
	}

}
