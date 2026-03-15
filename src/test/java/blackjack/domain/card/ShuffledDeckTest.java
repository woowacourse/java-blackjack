package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ShuffledDeckTest {

    @Test
    void CardsGenerator를_통해_덱_인스턴스를_생성한다() {
        assertThatCode(ShuffledDeck::create)
            .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("카드 인스턴스 하나를 반환한다")
    class ReturnCard {
        @Test
        void 남은_카드가_있다면_예외가_발생하지_않는다() {
            // given
            ShuffledDeck deck = ShuffledDeck.create();
            // when & then
            assertThatCode(deck::draw)
                .doesNotThrowAnyException();
        }

        @Test
        void null이_아닌_인스턴스를_반환한다() {
            // given
            ShuffledDeck deck = ShuffledDeck.create();
            // when
            Card card = deck.draw();
            // then
            assertThat(card).isNotNull();
        }

        @Test
        void 남은_카드가_없다면_예외를_던진다() {
            // given
            ShuffledDeck emptyDeck = new ShuffledDeck(List.of());
            // when & then
            assertThatThrownBy(emptyDeck::draw)
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Test
    void 덱을_생성하면_모든_경우의_수의_중복이_없는_카드들이_생성한다() {
        // given & when
        ShuffledDeck deck = ShuffledDeck.create();
        // then
        int expectedCardCount = Rank.values().length * Suit.values().length;

        Set<Card> uniqueCards = new HashSet<>();
        for (int i = 0; i < expectedCardCount; i++) {
            uniqueCards.add(deck.draw());
        }

        assertThat(uniqueCards).hasSize(expectedCardCount);
    }
}
