package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.result.PlayerOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamblerTest {

    private CardDeck cardDeck;
    private Gambler gambler;

    @BeforeEach
    void resetVariable() {
        cardDeck = new CardDeck();
        gambler = new Gambler(new Name("Jamie"), BettingMoney.of("10000"));
    }

    @DisplayName("생성시 Null일 경우 예외 발생")
    @Test
    void Player_Null_ThrownException() {
        assertThatThrownBy(() -> new Gambler(null, BettingMoney.of("10000")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new Gambler(new Name("jamie"), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new Gambler(new Name("jamie"), BettingMoney.of("10000"), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
    }

    @DisplayName("드로우 가능여부 - 가능")
    @Test
    void canDrawCard() {
        gambler.drawCard(cardDeck);
        assertThat(gambler.canDraw()).isTrue();

        for (int i = 0; i < 12; i++) {
            gambler.drawCard(cardDeck);
        }
        assertThat(gambler.canDraw()).isFalse();
    }

    @DisplayName("딜러와 비교 후 수익금액 가져옴")
    @Test
    void getProfitByComparing() {
        assertThat(gambler.getProfit(PlayerOutcome.WIN)).isEqualTo(10000);
    }
}
