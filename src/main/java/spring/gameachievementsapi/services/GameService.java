package spring.gameachievementsapi.services;

import spring.gameachievementsapi.model.Achievement;
import spring.gameachievementsapi.model.Game;

import java.util.List;
import java.util.UUID;

public interface GameService
{
    List<Achievement> getGameAchievements(UUID uuid);

    Game findById(UUID uuid);
}
