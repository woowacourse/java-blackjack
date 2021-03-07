package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        List<String> names = Stream.<String>builder()
            .add("A").add("B").add("C")
            .build()
            .collect(Collectors.toList());

        this.game = new Game(names);
    }

    @DisplayName("게임 생성 : 플레이어 및 딜러 생성")
    @Test
    void new_notNull() {
        assertAll(
            () -> assertNotNull(game.getDealer()),
            () -> assertEquals(game.getPlayers().size(), 3)
        );
    }

    @DisplayName("게임 결과 DTO 반환 테스트")
    @Test
    void getResultDtos_validSize() {
        assertThat(game.getResultDTOs()).hasSize(4);
    }

    @DisplayName("게임 승패 DTO 반환 테스트")
    @Test
    void getWinningResultDtos_validSize() {
        assertThat(game.getWinningResultDTOs()).hasSize(3);
    }

    @DisplayName("딜러 getter 테스트")
    @Test
    void getDealer_notNull() {
        assertThat(game.getDealer()).isNotNull();
    }

    @DisplayName("플레이어 리스 getter 테스트")
    @Test
    void getPlayers_notNull() {
        assertThat(game.getPlayers()).isNotNull();
    }
}