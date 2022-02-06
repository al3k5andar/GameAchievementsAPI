package spring.gameachievementsapi.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import spring.gameachievementsapi.model.Achievement;
import spring.gameachievementsapi.model.Game;
import spring.gameachievementsapi.repositories.GameRepository;
import spring.gameachievementsapi.services.GameService;

import java.util.*;
import java.util.function.Supplier;

@Service
public class GameServiceImpl implements GameService {

    private Logger logger= LogManager.getLogger(GameServiceImpl.class.getName());
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game findById(UUID uuid) {
        logger.info("Searching for Game with ID: {}", uuid);
        Supplier<NoSuchElementException> supplier= () -> new NoSuchElementException("There isn't Game with ID: "+ uuid);
        return gameRepository.findById(uuid).orElseThrow(supplier);
    }

    @Override
    public List<Achievement> getGameAchievements(UUID uuid) {
        logger.info("Get all Achievements for Game with ID: {}", uuid);
        Game theGame= gameRepository.getById(uuid);
        List<Achievement> achievementList= theGame.getAchievementList();
        achievementList.sort(comparator());
        return achievementList;
    }

    private Comparator<Achievement> comparator(){
        return new Comparator<Achievement>() {
            @Override
            public int compare(Achievement o1, Achievement o2) {
                if(o1.getDisplayOrder()< o2.getDisplayOrder())
                    return -1;
                if(o1.getDisplayOrder()== o2.getDisplayOrder()){
                    return o1.getDisplayName().compareTo(o2.getDisplayName());
                }
                return 1;
            }
        };
    }
}
