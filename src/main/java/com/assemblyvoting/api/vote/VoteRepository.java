package com.assemblyvoting.api.vote;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assemblyvoting.api.agenda.AgendaEntity;

public interface VoteRepository extends JpaRepository<VoteEntity, Integer>{
	Optional<List<VoteEntity>> findByAgenda(AgendaEntity agenda);
}
