package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("card를 추가한다")
    @Test
    void addCard() {
        Cards cards = new Cards();
        Card card1 = new Card(Denomination.TWO, Suits.HEART);
        Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
        cards.addCard(card1);
        cards.addCard(card2);

        assertThat(cards.getCards()).containsExactly(card1, card2);
    }
}
