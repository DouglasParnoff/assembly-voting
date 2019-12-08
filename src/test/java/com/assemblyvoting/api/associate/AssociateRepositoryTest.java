package com.assemblyvoting.api.associate;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AssociateRepositoryTest {
	
	@Autowired
	AssociateRepository associateRepository;
	
	private static final String INVALIDCPF = "invalidcpf";
	
	@Before
	public void setUp() throws Exception {
		AssociateEntity associate = new AssociateEntity();
		associate.setCpf(INVALIDCPF);
		associate.setName("Some Person Name");
		this.associateRepository.save(associate);
	}
	
	@After
	public final void tearDown(){
		this.associateRepository.deleteAll();
	}
	
	@Test
	public void findByCpf() {
		Optional<AssociateEntity> associate = this.associateRepository.findById(INVALIDCPF);
		assertEquals(INVALIDCPF, associate.get().getCpf());
	}
}
