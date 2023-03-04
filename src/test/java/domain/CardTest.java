package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드는 자신의 점수를 반환한다.")
    void getScore() {
        Card card = new Card(Denomination.KING, Suit.SPADE);
        Denomination denomination = card.getDenomination();
        Suit suit = card.getSuit();
        Assertions.assertThat(denomination).isEqualTo(Denomination.KING);
        Assertions.assertThat(suit).isEqualTo(Suit.SPADE);
    }
}
