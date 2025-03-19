package blackjack.model.card.initializer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.BlackJackCards;
import org.junit.jupiter.api.Test;

class DefaultCardDeckInitializerTest {

    private final DefaultCardDeckInitializer defaultCardDeckInitializer = new DefaultCardDeckInitializer();

    @Test
    void 랜덤으로_카드를_52장_생성해준다() {
        BlackJackCards cardDeck = defaultCardDeckInitializer.initialize();

        assertThat(cardDeck.getValues()).hasSize(52);
    }

}
