package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    @DisplayName("pickCard 에서 카드를 잘 뽑는지")
    void PickCard_Return_Only_One() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.pickCard()).isInstanceOf(Card.class);
    }
}
