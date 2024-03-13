package blackjack.domain.card;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardHandTest {

    @Test
    void 카드를_받을_수_있다() {
        final CardHand cardHand = new CardHand();
        cardHand.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));

        assertThat(cardHand).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .containsExactly(new Card(Suit.DIAMOND, Denomination.KING));
    }
}
