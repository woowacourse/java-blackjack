package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        Map<String, Double> playerMoney = new HashMap<>();
        playerMoney.put("A", 10000.0);
        playerMoney.put("B", 10000.0);
        playerMoney.put("C", 10000.0);

        this.game = new Game(playerMoney);
    }

    @DisplayName("게임 생성 : 플레이어 및 딜러 생성")
    @Test
    void new_notNull() {
        assertAll(
            () -> assertNotNull(game.getDealer()),
            () -> assertEquals(game.getPlayers().size(), 3)
        );
    }

    @DisplayName("게임 승패 DTO 반환 테스트")
    @Test
    void getWinningResults_validSize() {
        assertThat(game.getWinningResults()).hasSize(3);
    }

    @DisplayName("딜러 getter 테스트")
    @Test
    void getDealer_notNull() {
        assertThat(game.getDealer()).isNotNull();
    }

    @DisplayName("게임 전체 유저 리스트 getter 테스트")
    @Test
    void getUsers_validSize() {
        assertThat(game.getUsers()).hasSize(4);
    }

    @DisplayName("플레이어 리스트 getter 테스트")
    @Test
    void getPlayers_notNull() {
        assertThat(game.getPlayers()).isNotNull();
    }
}