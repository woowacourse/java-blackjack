package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에 있는 카드는 총 52개이다")
    @Test
    void deckHaveFiftyTwoCards() {
        Deck deck = new Deck();
        Assertions.assertThat(deck).extracting("cards").asList().hasSize(52);
    }

    @DisplayName("덱 제일 앞에서 카드를 지급한다.")
    @Test
    void getSizeDynamicTest() {
        Deck deck = new Deck();
        Assertions.assertThat(deck.draw()).isEqualTo(new Card(Denomination.ACE, Suit.SPADE));
        Assertions.assertThat(deck).extracting("cards").asList().hasSize(51);
    }
}
