package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱_드로우_테스트() {
        // given, when
        Card card1 = Card.of(Denomination.EIGHT, Shape.CLUBS);
        Card card2 = Card.of(Denomination.FOUR, Shape.DIAMONDS);
        List<Card> cards = new ArrayList<>(Arrays.asList(card1, card2));
        Deck deck = new Deck(cards);

        // then
        assertThat(deck.draw()).isEqualTo(card1);
        assertThat(deck.draw()).isEqualTo(card2);
    }

    @DisplayName("빈 덱에서 드로우를 하면 예외가 발생한다.")
    @Test
    void 빈_덱_드로우_테스트() {
        // given, when
        Deck deck = new Deck(Collections.emptyList());

        // then
        assertThatThrownBy(() -> deck.draw())
            .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

}
