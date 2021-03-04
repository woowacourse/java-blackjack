package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @DisplayName("덱에서 카드를 1장 드로우한다.")
    @Test
    void getCardTest() {
        Deck deck = new Deck();
        assertThat(deck.draw()).isEqualTo(new Card(Type.CLUB, Denomination.KING));
        assertThat(deck.draw()).isEqualTo(new Card(Type.CLUB, Denomination.QUEEN));
    }
}
