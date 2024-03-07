package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱에서 카드를 하나 뽑는다.")
    @Test
    void validateDuplicate() {
        // given
        Card expected = new Card(CardNumber.TWO, CardShape.CLOVER);
        Deck deck = new Deck(Set.of(expected));
        // when
        Card card = deck.pick();

        // then
        assertThat(card).isEqualTo(expected);
    }
}
