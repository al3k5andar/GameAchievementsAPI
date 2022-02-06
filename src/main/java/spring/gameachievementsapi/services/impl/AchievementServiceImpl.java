package spring.gameachievementsapi.services.impl;

import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.springframework.stereotype.Service;
import spring.gameachievementsapi.model.Achievement;
import spring.gameachievementsapi.model.Game;
import spring.gameachievementsapi.repositories.AchievementRepository;
import spring.gameachievementsapi.repositories.GameRepository;
import spring.gameachievementsapi.services.AchievementService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@Log4j
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final GameRepository gameRepository;

    private Logger logger= LogManager.getLogger(AchievementServiceImpl.class.getName());

    public AchievementServiceImpl(AchievementRepository achievementRepository, GameRepository gameRepository) {
        this.achievementRepository = achievementRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Achievement save(Achievement achievement, UUID gameUUID) {
        logger.info("Creating new Achievement: {}", achievement.getDisplayName());
        Game game= gameRepository.getById(gameUUID);
        achievement.setId(UUID.randomUUID());
        achievement.setGame(game);
        LocalDateTime currentDateTime= LocalDateTime.now();
        achievement.setCreatedAt(currentDateTime);
        achievement.setUpdatedAt(currentDateTime);
        return achievementRepository.save(achievement);
    }

    @Override
    public Achievement findById(UUID uuid) {
        logger.info("Searching for Achievement with ID: {}", uuid);
        Supplier<NoSuchElementException> supplier= () -> new NoSuchElementException("There isn't Achievement with ID: "+ uuid);
        return achievementRepository.findById(uuid).orElseThrow(supplier);
    }

    @Override
    public Achievement update(Achievement achievement, UUID uuid) {
        logger.info("Updating existing Achievement with ID: {}", uuid);
        Achievement existedAchievement= achievementRepository.getById(uuid);
        if(existedAchievement!= null){
            if(achievement.getDisplayName()!= null)
                existedAchievement.setDisplayName(achievement.getDisplayName());
            if(achievement.getDescription()!= null)
                existedAchievement.setDescription(achievement.getDescription());
            if(achievement.getIcon()!= null)
                existedAchievement.setIcon(achievement.getIcon());
            if(achievement.getDisplayOrder()!= null)
                existedAchievement.setDisplayOrder(achievement.getDisplayOrder());
            existedAchievement.setUpdatedAt(LocalDateTime.now());
            return achievementRepository.save(existedAchievement);
        }
        else {
            throw new NoSuchElementException("Achievement with ID: " + uuid +" not found");
        }

    }

    @Override
    public boolean delete(UUID uuid) {
        logger.info("Deleting Achievement with ID: {}", uuid);
        achievementRepository.deleteById(uuid);
        return true;
    }
}
