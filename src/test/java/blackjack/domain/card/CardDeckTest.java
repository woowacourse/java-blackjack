package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.generator.RandomCardDeckGenerator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("사용한 카드는 제거한다")
    void drawCard() {
        final CardDeck cardDeck = new CardDeck(new RandomCardDeckGenerator());
        final int deckSize = 52;

        for (int i = 0; i < deckSize; i++) {
            cardDeck.draw();
        }

        assertThatThrownBy(cardDeck::draw)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("처음 플레이어에게 draw 하는 카드 수는 2장이다.")
    void drawInitialCards() {
        final CardDeck cardDeck = new CardDeck(new RandomCardDeckGenerator());
        final int expected = 2;

        final Cards cards = cardDeck.drawInitialCards();
        final int actual = cards.getCards().size();

        assertThat(actual).isEqualTo(expected);
    }
}
