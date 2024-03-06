package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에서 카드를 하나 뽑는다.")
    @Test
    void validateDuplicate() {
        // given
        Deck deck = new Deck();

        // when
        Card card = deck.pick();

        // then
        assertThat(card).isEqualTo(new Card(CardNumber.TWO, CardShape.CLOVER));
    }
}
