package com.assemblyvoting.api.session;

import java.util.Date;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "session")
public class SessionEntity {
	private int id;
	private int openTime;
	private Date creationDatetime;
	private Date endDatetime;
	private SessionStatusEnum status;
	private AgendaEntity agenda;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "open_time")
	public int getOpenTime() {
		return openTime;
	}

	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	@Column(name = "creation_datetime")
	public Date getCreationDatetime() {
		return creationDatetime;
	}

	public void setCreationDatetime(Date creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	@Column(name = "end_datetime")
	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDate(Date endDate) {
		this.endDatetime = endDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public SessionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SessionStatusEnum status) {
		this.status = status;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subject", referencedColumnName = "subject")
	public AgendaEntity getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaEntity agenda) {
		this.agenda = agenda;
	}

}
