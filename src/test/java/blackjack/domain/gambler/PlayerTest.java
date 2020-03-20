package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
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
        player = new Player(new Name("Jamie&Ravie"), BettingMoney.of("10000"));
    }

    @DisplayName("생성시 Null일 경우 예외 발생")
    @Test
    void Player_Null_ThrownException() {
        assertThatThrownBy(() -> new Player(null, BettingMoney.of("10000")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new Player(new Name("jamie"), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new Player(new Name("jamie"), BettingMoney.of("10000"), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
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

    @DisplayName("딜러와 비교 후 수익금액 가져옴")
    @Test
    void getProfitByComparing() {
        assertThat(player.getBettingMoneyMultiply(1.0)).isEqualTo(10000);
    }
}
