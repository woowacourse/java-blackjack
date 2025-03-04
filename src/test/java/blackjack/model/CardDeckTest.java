package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.Test;

class CardDeckTest {

    private static final int TOTAL_CARD_SIZE = 52;

    @Test
    void 카드_덱을_초기화한다() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.getCards())
                .hasSize(TOTAL_CARD_SIZE);
    }

    @Test
    void 랜덤으로_N_개의_카드를_반환한다() {
        CardDeck cardDeck = new CardDeck();
        int drawSize = 4;

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(cardDeck.draw(drawSize)).hasSize(drawSize);
            softAssertions.assertThat(cardDeck.getCards()).hasSize(TOTAL_CARD_SIZE - drawSize);
        });
    }
}
