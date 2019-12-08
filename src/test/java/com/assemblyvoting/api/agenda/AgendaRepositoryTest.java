package com.assemblyvoting.api.agenda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.assemblyvoting.api.associate.AssociateEntity;
import com.assemblyvoting.api.associate.AssociateRepository;
import com.assemblyvoting.api.session.SessionEntity;
import com.assemblyvoting.api.session.SessionRepository;
import com.assemblyvoting.api.session.SessionStatusEnum;
import com.assemblyvoting.api.vote.VoteEntity;
import com.assemblyvoting.api.vote.VoteEnum;
import com.assemblyvoting.api.vote.VoteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AgendaRepositoryTest {
	
	@Autowired
	AgendaRepository agendaRepository;
	
	@Autowired
	AssociateRepository associateRepository;

	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	private String agendaLonely = "Agenda to check if UT have worked well";
	private String agendaWithRelation = "Agenda with relations";
	private int relationsQt = 10;
	private SessionEntity expectedSession;
	@Before
	public void setUp() {
		//just one agenda
		AgendaEntity agenda = new AgendaEntity();
		agenda.setSubject(agendaLonely);
		agenda.setResult(AgendaResultEnum.WAITING);		
		agenda = agendaRepository.save(agenda);
		
		//to validate just the relations
		AgendaEntity agendaRelat = new AgendaEntity();
		agendaRelat.setSubject(agendaWithRelation);
		agendaRelat.setResult(AgendaResultEnum.WAITING);		
		agendaRelat = agendaRepository.save(agendaRelat);
		
		AssociateEntity associate;
		VoteEntity vote = new VoteEntity();
		
		for (int i = 0; i < relationsQt; i++) {
			associate = new AssociateEntity();
			associate.setCpf("testing " + i);
			associate.setName("Some Person Name " + i);
			associate = associateRepository.save(associate);
			
			vote = new VoteEntity();
			vote.setAssociate(associate);
			vote.setAgenda(agendaRelat);
			vote.setChoise(i % 2 == 0 ? VoteEnum.YES : VoteEnum.NO);
			vote.setAssociate(associate);
			voteRepository.save(vote);
		}
		
		SessionEntity session = new SessionEntity();		
		session.setOpenTime(666);
		session.setStatus(SessionStatusEnum.OPEN);
		session.setAgenda(agendaRelat);
		expectedSession = sessionRepository.saveAndFlush((session));
		
	}
	
	@After
	public void tearDown() {
		this.agendaRepository.deleteAll();
	}
	
	@Test
	public void findById() {
		Optional<AgendaEntity> agenda = agendaRepository.findById(agendaLonely);
		assertEquals(true, agenda.isPresent(), "agendaLonely is present");
		assertEquals(agendaLonely, agenda.get().getSubject(), "agendaLonely subject");
	}
	
	@Test
	public void findRelatedSession() {
		Optional<AgendaEntity> agenda = agendaRepository.findById(agendaWithRelation);
		assertEquals(true, agenda.isPresent(), "agendaWithRelation is isPresent");
		assertEquals(expectedSession.getId(), agenda.get().getSession().getId(), "agendaWithRelation session found");
		assertEquals(expectedSession.getCreationDatetime(), agenda.get().getSession().getCreationDatetime(), "agendaWithRelation session creationDatetime");
	}
	
	@Test
	public void countRelatedVotes() {			
		Optional<AgendaEntity> agenda = agendaRepository.findById(agendaWithRelation);
		assertEquals(true, agenda.isPresent(), "agendaWithRelation is isPresent");		
		Optional<List<VoteEntity>> votes = voteRepository.findByAgenda(agenda.get());
		assertEquals(true, votes.isPresent(), "agendaWithRelation votes list is isPresent");
		assertEquals(votes.get().size(), relationsQt, "count of agendaWithRelation votes");
	}
}
