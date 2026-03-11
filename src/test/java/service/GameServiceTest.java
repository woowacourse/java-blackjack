package service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {
    GameService gameService;

    public GameServiceTest() {
        this.gameService = new GameService();
    }

    @Test
    void 플레이어_등록_테스트(){
        List<String> playerNames = new ArrayList<>(List.of("pobi", "jason"));

        gameService.joinPlayers(playerNames);
        assertThat(gameService.getPlayerGroupSize()).isEqualTo(playerNames.size());
    }
}
