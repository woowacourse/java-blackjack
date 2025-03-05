package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardInitializerTest {

    @Test
    void 카드를_52장_생성해준다() {
        Cards cardDeck = CardInitializer.createCardDeck();

        assertThat(cardDeck.getValues()).hasSize(52);
    }

}
