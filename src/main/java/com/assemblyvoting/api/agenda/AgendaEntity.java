package com.assemblyvoting.api.agenda;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.assemblyvoting.api.session.SessionEntity;
import com.assemblyvoting.api.vote.VoteEntity;

@Entity
@Table(name = "agenda")
public class AgendaEntity {

	private String subject;
	private AgendaResultEnum result;
	private Date creationDatetime;
	private SessionEntity session;
	private VoteEntity vote;

	@Id
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public AgendaResultEnum getResult() {
		return result;
	}

	public void setResult(AgendaResultEnum result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_datatime")
	public Date getCreationDatetime() {
		return creationDatetime;
	}

	public void setCreationDatetime(Date creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	@PrePersist
	public void prePersist() {
		final Date currentDt = new Date();
		this.creationDatetime = currentDt;
	}

	@OneToOne
	public SessionEntity getSession() {
		return session;
	}

	public void setSession(SessionEntity session) {
		this.session = session;
	}

	@OneToOne	
	public VoteEntity getVote() {
		return vote;
	}

	public void setVote(VoteEntity vote) {
		this.vote = vote;
	}

}
