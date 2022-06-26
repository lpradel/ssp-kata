package com.lukaspradel.ssp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<Player, Integer> {
}
