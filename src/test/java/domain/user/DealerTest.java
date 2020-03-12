package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private CardDeck cardDeck;
    private Dealer dealer;

    @BeforeEach
    void resetVariable() {
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
}
