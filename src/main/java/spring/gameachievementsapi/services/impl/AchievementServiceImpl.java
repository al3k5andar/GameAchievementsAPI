package spring.gameachievementsapi.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final GameRepository gameRepository;

    private final Logger logger= LogManager.getLogger(AchievementServiceImpl.class.getName());

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
        Achievement existedAchievement= this.findById(uuid);

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

    @Override
    public boolean delete(UUID uuid) {
        logger.info("Deleting Achievement with ID: {}", uuid);
        achievementRepository.deleteById(uuid);
        return true;
    }
}
