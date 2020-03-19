package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Money;
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
        player = new Player(new Name("Jamie&Ravie"), Money.fromPositive("10000"));
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
        Dealer dealer = new Dealer();
        dealer.drawCard(cardDeck, 10);
        player.drawCard(cardDeck);
        assertThat(player.getProfitByComparing(dealer).getMoney())
            .isEqualTo(10000);
    }
}
