package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("사용한 카드는 제거한다")
    void drawCard() {
        final CardDeck cardDeck = new CardDeck(new CardDeckGenerator());
        final int expected = cardDeck.size() - 1;

        cardDeck.draw();
        final int actual = cardDeck.size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("처음 플레이어에게 draw 하는 카드 수는 2장이다.")
    void drawInitialCards() {
        final CardDeck cardDeck = new CardDeck(new CardDeckGenerator());
        final int expected = cardDeck.size() - 2;

        cardDeck.drawInitialCards();
        final int actual = cardDeck.size();

        assertThat(actual).isEqualTo(expected);
    }
}
