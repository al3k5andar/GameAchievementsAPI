package spring.gameachievementsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.gameachievementsapi.model.Achievement;

import java.util.Optional;
import java.util.UUID;

public interface AchievementRepository extends JpaRepository<Achievement, UUID> {

    Optional<Achievement> findById(UUID uuid);
}
