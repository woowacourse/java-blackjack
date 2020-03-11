package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("드로우 테스트")
    void isAbleDrawCard() {
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("jamie");
        player.drawCard(cardDeck);
        Assertions.assertThat(player.isAbleDrawCards())
                .isTrue();
        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        Assertions.assertThat(player.isAbleDrawCards())
                .isFalse();
    }

    @Test
    @DisplayName("Bust 패배 테스트")
    void isBustEqualLose() {
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("하비");
        player.isAbleDrawCards();
        Assertions.assertThat(player.getWinningResult()).isEqualTo(WinningResult.UNDEFINED);

        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        player.isAbleDrawCards();
        Assertions.assertThat(player.getWinningResult()).isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("승무패 결과 산정 테스트 - 패배")
    void calculateWinningResult_lose() {
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("제이미");
        player.drawCard(cardDeck);
        player.calculateWinningResult(17);
        Assertions.assertThat(player.getWinningResult())
                .isEqualTo(WinningResult.LOSE);
    }

    @Test
    @DisplayName("승무패 결과 산정 테스트 - 승리")
    void calculateWinningResult_win() {
        CardDeck cardDeck = new CardDeck();
        Player player = new Player("제이미");
        player.drawCard(cardDeck);
        player.calculateWinningResult(22);
        Assertions.assertThat(player.getWinningResult())
                .isEqualTo(WinningResult.WIN);
    }
}
