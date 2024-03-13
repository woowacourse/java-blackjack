package blackjack.domain.card;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    void 처음_카드_덱의_총_카드_수는_52장이다() {
        final CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck).extracting("cardDeck", InstanceOfAssertFactories.list(Card.class))
                .hasSize(52);
    }
}
