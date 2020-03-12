package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Outcome;
import blackjack.domain.card.CardDeck;
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

    @DisplayName("드로우 가능여부 - 가능")
    @Test
    void canDrawCard() {
        player.drawCard(cardDeck);
        assertThat(player.canDrawCard()).isTrue();

        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        assertThat(player.canDrawCard()).isFalse();
    }

    @DisplayName("Bust일 때(21 초과일 때), 무조건 패배하는 테스트")
    @Test
    void isBustEqualLose() {
        for (int i = 0; i < 12; i++) {
            player.drawCard(cardDeck);
        }
        assertThat(player.calculateOutcome(17)).isEqualTo(Outcome.LOSE);
    }

    @DisplayName("승무패 결과 산정 테스트")
    @Test
    void calculateWinningResult_lose() {
        player.drawCard(cardDeck);
        assertThat(player.calculateOutcome(17)).isEqualTo(Outcome.LOSE);
        assertThat(player.calculateOutcome(player.getScore())).isEqualTo(Outcome.DRAW);
        assertThat(player.calculateOutcome(22)).isEqualTo(Outcome.WIN);
    }
}
