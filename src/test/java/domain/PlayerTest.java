package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    CardDeck cardDeck = new CardDeck();
    Player player;

    @BeforeEach
    void init() {
        player = new Player(new Name("jamie"));
    }

    @Test
    @DisplayName("드로우 테스트")
    void isAbleDrawCard() {
        Assertions.assertThat(player.canHit())
                .isTrue();
        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        Assertions.assertThat(player.canHit())
                .isFalse();
    }

    @Test
    @DisplayName("Bust 패배 테스트")
    void isBustEqualLose() {
        player.canHit();
        Assertions.assertThat(player.getWinningResult()).isEqualTo(WinningResult.UNDEFINED);

        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        player.canHit();
        Assertions.assertThat(player.getWinningResult()).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("승무패 결과 산정 테스트 - 패배")
    void calculateWinningResult_lose() {
        player.drawCard(cardDeck);
        player.calculateWinningResult(17);
        Assertions.assertThat(player.getWinningResult())
                .isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("승무패 결과 산정 테스트 - 승리")
    void calculateWinningResult_win() {
        player.drawCard(cardDeck);
        player.calculateWinningResult(22);
        Assertions.assertThat(player.getWinningResult())
                .isEqualTo(WinningResult.WIN);
    }
}
