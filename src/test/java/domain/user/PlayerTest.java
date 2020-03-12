package domain.user;

import domain.WinningResult;
import domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private CardDeck cardDeck;
    private Player player;

    @BeforeEach
    void resetVariable() {
        cardDeck = new CardDeck();
        player = new Player("Jamie&Ravie");
    }

    @DisplayName("드로우 가능여부 테스트")
    @Test
    void canDrawCard() {
        player.drawCard(cardDeck);
        Assertions.assertThat(player.canDrawCard()).isTrue();

        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        Assertions.assertThat(player.canDrawCard()).isFalse();
    }

    @DisplayName("Bust일 때(21 초과일 때), 무조건 패배하는 테스트")
    @Test
    void isBustEqualLose() {
        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        player.calculateWinningResult(17);
        Assertions.assertThat(player.getWinningResult()).isEqualTo(WinningResult.LOSE);
    }

    @DisplayName("승무패 결과 산정 테스트 - 패배")
    @Test
    void calculateWinningResult_lose() {
        player.drawCard(cardDeck);
        player.calculateWinningResult(17);
        Assertions.assertThat(player.getWinningResult())
            .isEqualTo(WinningResult.LOSE);
    }

    @DisplayName("승무패 결과 산정 테스트 - 승리")
    @Test
    void calculateWinningResult_win() {
        player.drawCard(cardDeck);
        player.calculateWinningResult(22);
        Assertions.assertThat(player.getWinningResult())
            .isEqualTo(WinningResult.WIN);
    }
}
