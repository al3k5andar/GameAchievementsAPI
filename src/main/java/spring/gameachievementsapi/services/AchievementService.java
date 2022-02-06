package spring.gameachievementsapi.services;

import spring.gameachievementsapi.model.Achievement;

import java.util.UUID;

public interface AchievementService
{
    Achievement save(Achievement achievement, UUID gameUUID);

    Achievement findById(UUID uuid);

    Achievement update(Achievement achievement, UUID uuid);

    boolean delete(UUID uuid);
}
