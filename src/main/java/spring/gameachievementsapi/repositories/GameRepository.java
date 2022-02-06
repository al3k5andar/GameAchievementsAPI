package spring.gameachievementsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.gameachievementsapi.model.Game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

    Optional<Game> findById(UUID uuid);
}
