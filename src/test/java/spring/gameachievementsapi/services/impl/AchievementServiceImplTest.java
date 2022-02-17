package spring.gameachievementsapi.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.gameachievementsapi.model.Achievement;
import spring.gameachievementsapi.model.Game;
import spring.gameachievementsapi.repositories.GameRepository;
import spring.gameachievementsapi.services.AchievementService;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AchievementServiceImplTest {

    @Autowired
    private AchievementService achievementService;
    @Autowired
    private GameRepository gameRepository;

    @Test
    void findById() {
//        Given
        Achievement savedAchievement= getAchievement();

//        When
        Achievement theAchievement= achievementService.findById(savedAchievement.getId());

//        Then
        Assertions.assertEquals(theAchievement.getId(), savedAchievement.getId());
        Assertions.assertEquals("Lost History", theAchievement.getDisplayName());
    }

    @Test
    @Transactional
    void update() {
//        Given
        Achievement savedAchievement= getAchievement();

        Achievement updatingAchievement= new Achievement();
        updatingAchievement.setDisplayName("Forgotten History");

//        When
        Achievement theAchievement= achievementService.update(updatingAchievement, savedAchievement.getId());

//        Then
        Assertions.assertEquals("Forgotten History", theAchievement.getDisplayName());
    }

    @Test
    void delete() {
//        Given
        Achievement savedAchievement= getAchievement();

//        When
        boolean isTrue= achievementService.delete(savedAchievement.getId());

//        Then
        Assertions.assertTrue(isTrue);
    }

    private Achievement getAchievement(){
        Game game= new Game();
        game.setId(UUID.randomUUID());
        game.setDisplayName("Uncharted 4");
        gameRepository.save(game);
        Achievement achievement= new Achievement();
        achievement.setDisplayName("Lost History");
        achievement.setDescription("Find All Journal Notes");

        return achievementService.save(achievement, game.getId());
    }
}