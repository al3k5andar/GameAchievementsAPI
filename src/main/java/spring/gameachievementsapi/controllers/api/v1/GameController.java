package spring.gameachievementsapi.controllers.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.gameachievementsapi.model.Response;
import spring.gameachievementsapi.services.GameService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Response> getAllGameAchievements(@PathVariable("gameId")UUID gameId){
        return ResponseEntity.ok(Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Get list of all game achievements")
                        .data(Map.of("achievements", gameService.getGameAchievements(gameId)))
                .build()
        );
    }
}
