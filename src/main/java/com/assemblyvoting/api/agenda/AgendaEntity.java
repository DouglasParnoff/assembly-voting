package com.assemblyvoting.api.agenda;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private List<VoteEntity> votes;

	@Id
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@PrePersist
	public void prePersist() {
		final Date currentDt = new Date();
		this.creationDatetime = currentDt;
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

	@OneToOne(mappedBy = "agenda", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public SessionEntity getSession() {
		return session;
	}

	public void setSession(SessionEntity session) {
		this.session = session;
	}

	@OneToMany(mappedBy = "agenda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	public List<VoteEntity> getVotes() {
		return votes;
	}

	public void setVotes(List<VoteEntity> votes) {
		this.votes = votes;
	}

}
