package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    @Test
    void 카드_덱의_총_카드_수는_52장이다() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(52);
    }
}
