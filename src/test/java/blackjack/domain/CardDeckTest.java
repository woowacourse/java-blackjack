package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("사용한 카드는 제거한다")
    void drawCard() {
        final CardDeck cardDeck = CardDeckGenerator.createCardDeckByCardNumber();
        final int expected = 51;

        cardDeck.drawCard();
        final int actual = cardDeck.size();
        assertThat(actual).isEqualTo(expected);
    }
}
