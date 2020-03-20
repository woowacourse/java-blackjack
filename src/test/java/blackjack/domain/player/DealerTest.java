package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private static CardDeck cardDeck;
    private static Dealer dealer;

    @BeforeAll
    static void resetVariable() {
        cardDeck = new CardDeck();
        dealer = new Dealer();
    }

    @DisplayName("드로우 가능여부")
    @Test
    void canDrawCard() {
        dealer.drawCard(cardDeck);
        assertThat(dealer.canDrawCard()).isTrue();

        for (int i = 0; i < 10; i++) {
            dealer.drawCard(cardDeck);
        }
        assertThat(dealer.canDrawCard()).isFalse();
    }

    @DisplayName("Null 인자 - 예외발생")
    @Test
    void Dealer_Null_ThrownException() {
        assertThatThrownBy(() -> new Dealer(null)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
    }
}
