package spring.gameachievementsapi.controllers.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.gameachievementsapi.model.Achievement;
import spring.gameachievementsapi.model.Response;
import spring.gameachievementsapi.services.AchievementService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/achievement")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAchievementById(@PathVariable("id") UUID id){
        Achievement achievement= achievementService.findById(id);
        return ResponseEntity.ok(Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Get Achievement by ID")
                        .data(Map.of("achievement", achievement))
                .build()
        );
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<Response> createAchievement(@Valid @RequestBody Achievement achievement, @PathVariable("gameId") UUID gameId){
        return ResponseEntity.ok(Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED)
                        .message("Creating new Achievement")
                        .data(Map.of("Achievement",achievementService.save(achievement, gameId)))
                .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateAchievement(@RequestBody Achievement achievement, @PathVariable("id") UUID id){
        return ResponseEntity.ok(Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Update Achievement with ID: "+ id)
                        .data(Map.of("achievement",achievementService.update(achievement, id)))
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAchievement(@PathVariable("id") UUID id){
        return ResponseEntity.ok(Response.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .message("Deleting Achievement with ID: "+ id)
                        .data(Map.of("achievement", achievementService.delete(id)))
                .build()
        );
    }
}
