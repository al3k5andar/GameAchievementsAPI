package spring.gameachievementsapi.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.gameachievementsapi.model.Achievement;
import spring.gameachievementsapi.model.Game;
import spring.gameachievementsapi.repositories.GameRepository;
import spring.gameachievementsapi.services.AchievementService;
import spring.gameachievementsapi.services.GameService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameServiceImplTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AchievementService achievementService;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
        gameService= new GameServiceImpl(gameRepository);
    }

    @Test
    void findById() {
//        Given
        UUID id= UUID.randomUUID();
        Game game= new Game();
        game.setId(id);
        game.setDisplayName("Uncharted 4");
        gameRepository.save(game);

//        When
        Game theGame= gameService.findById(id);

//        Then
        Assertions.assertEquals(id, theGame.getId());
        Assertions.assertEquals("Uncharted 4", theGame.getDisplayName());

    }

    @Test
    @Transactional
    void getGameAchievements() {
//        Given
        Achievement achievement1= new Achievement();
        achievement1.setId(UUID.randomUUID());
        achievement1.setDisplayName("First Treasure");
        achievement1.setDescription("Find a Treasure");
        achievement1.setDisplayOrder(2L);


        Achievement achievement2= new Achievement();
        achievement2.setId(UUID.randomUUID());
        achievement2.setDisplayName("Relic Finder");
        achievement2.setDescription("Find the Strange Relics");
        achievement2.setDisplayOrder(2L);

        Achievement achievement3= new Achievement();
        achievement3.setId(UUID.randomUUID());
        achievement3.setDisplayName("Lost Art of Journaling");
        achievement3.setDescription("Find all Journal Entries");
        achievement3.setDisplayOrder(1L);

        Achievement achievement4= new Achievement();
        achievement4.setId(UUID.randomUUID());
        achievement4.setDisplayName("Hangman's Bullet");
        achievement4.setDescription("Perform 20 headshots from the rope");
        achievement4.setDisplayOrder(3L);

        Game game= new Game();
        game.setId(UUID.randomUUID());
        Game savedGame= gameRepository.save(game);

        achievementService.save(achievement1, savedGame.getId());
        achievementService.save(achievement2, savedGame.getId());
        achievementService.save(achievement3, savedGame.getId());
        achievementService.save(achievement4, savedGame.getId());
        savedGame.setAchievementList(new ArrayList<>());
        savedGame.getAchievementList().addAll(List.of(achievement1, achievement2, achievement3, achievement4));
        gameRepository.save(savedGame);


//        When
        List<Achievement> achievementList= gameService.getGameAchievements(savedGame.getId());

//        Then
        Assertions.assertEquals(4, achievementList.size());
    }
}