package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Nested
    class 모든_경우의_수의_중복이_없는_카드들을_생성한다 {
        @Test
        void 모든_경우의_수의_카드들을_생성한다() {
            // given
            Deck deck = Deck.unique();

            // when
            List<Card> cards = drawEveryCards(deck);

            // then
            int rankSize = Rank.values().length;
            int suitSize = Suit.values().length;
            assertThat(cards.size()).isEqualTo(rankSize * suitSize);
        }

        @Test
        void 중복_없는_카드들을_생성한다() {
            // given
            Deck deck = Deck.unique();

            // when
            List<Card> cards = drawEveryCards(deck);

            // then
            long uniqueCardCount = cards.stream()
                    .distinct()
                    .count();
            assertThat(cards.size()).isEqualTo(uniqueCardCount);
        }
    }

    @Nested
    @DisplayName("카드 인스턴스 하나를 반환한다")
    class ReturnCard {
        @Test
        void 남은_카드가_있다면_예외가_발생하지_않는다() {
            // given
            Deck deck = Deck.unique();

            // when & then
            assertThatCode(deck::draw)
                    .doesNotThrowAnyException();
        }

        @Test
        void null이_아닌_인스턴스를_반환한다() {
            // given
            Deck deck = Deck.unique();

            // when
            Card card = deck.draw();

            // then
            assertThat(card).isNotNull();
        }

        @Test
        void 남은_카드가_없다면_예외를_던진다() {
            // given
            List<Card> emptyCards = List.of();
            Deck deck = new Deck(emptyCards);

            // when & then
            assertThatThrownBy(deck::draw)
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    private List<Card> drawEveryCards(Deck deck) {
        ArrayList<Card> cards = new ArrayList<>();

        try {
            drawUntilException(deck, cards);
        } catch (IllegalStateException ignored) {
            return cards;
        }

        return cards;
    }

    private void drawUntilException(Deck deck, List<Card> cards) {
        while (true) {
            cards.add(deck.draw());
        }
    }
}
