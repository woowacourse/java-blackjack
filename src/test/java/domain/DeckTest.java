package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Deck;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Nested
    @DisplayName("constructor(): ")
    class Constructor {
        @Test
        @DisplayName("[예외] - 사이즈가 52개인지 확인한다.")
        void invalidSize() {
            List<Card> cards = TestFixture.createCards();
            cards.add(new Card(Rank.ACE, Suit.SPADE));
            assertThatThrownBy(() -> Deck.createFromList(cards))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("[예외] - 카드가 중복되는지 확인한다.")
        void duplicate() {
            List<Card> cards = TestFixture.createCards();

            cards.removeLast();
            cards.add(cards.getFirst());    //중복 발생시키기

            assertThatThrownBy(() -> Deck.createFromList(cards))
                    .isInstanceOf(IllegalArgumentException.class);
        }

    }
}
