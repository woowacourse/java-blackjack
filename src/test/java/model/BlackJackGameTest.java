package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void init() {
        blackJackGame = new BlackJackGame(new Deck());
    }

    @Test
    @DisplayName("Player에게 카드를 나눠준다.")
    void divideCard() {
        // given
        final Players players = new Players(List.of("bebe", "ethan"));
        final Result result = new Result(players);

        // when
        for (Player player : players.getPlayers()) {
            blackJackGame.divideCard(result, player);
        }

        // then
        assertAll(
                () -> assertThat(result.getScoreBoards().get(new Player("bebe"))).hasSize(1),
                () -> assertThat(result.getScoreBoards().get(new Player("ethan"))).hasSize(1)
        );
    }
}
