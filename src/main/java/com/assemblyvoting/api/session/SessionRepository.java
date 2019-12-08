package com.assemblyvoting.api.session;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assemblyvoting.api.agenda.AgendaEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer>{
	Optional<SessionEntity> findByAgenda(AgendaEntity agenda);
}
