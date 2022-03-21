package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("한 장 뽑는다.")
    void drawCard() {
        final Card expected = Card.of(CardSymbol.CLUB, CardNumber.KING);
        final Deck deck = Deck.from(() -> List.of(expected));

        // when
        final Card actual = deck.drawCard();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("덱이 비어있으면 Null을 반환한다.")
    void emptyDeck() {
        final Deck deck = Deck.from(List::of);

        // when
        final Card actual = deck.drawCard();

        // then
        assertThat(actual).isNull();
    }
}
