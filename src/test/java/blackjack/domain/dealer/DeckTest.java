package blackjack.domain.dealer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("빈덱에서 카드를 뽑을 수 없다.")
    @Test
    void validateEmpty() {
        Deck deck = new Deck(List.of());

        assertThatThrownBy(deck::pick)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 부족합니다.");
    }

    @DisplayName("덱에서 카드를 하나 뽑는다.")
    @Test
    void validateDuplicate() {
        // given
        Card expected = new Card(CardNumber.TWO, CardShape.CLOVER);
        Deck deck = new Deck(List.of(expected));
        // when
        Card card = deck.pick();

        // then
        assertThat(card).isEqualTo(expected);
    }
}
