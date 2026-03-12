package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    private final CardsGenerator generator = new ShuffledCardsGenerator();
    private final CardsGenerator emptyCardsGenerator = List::of;

    @Test
    void CardsGenerator를_통해_덱_인스턴스를_생성한다() {
        assertThatCode(() -> Deck.create(generator))
            .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("카드 인스턴스 하나를 반환한다")
    class ReturnCard {
        @Test
        void 남은_카드가_있다면_예외가_발생하지_않는다() {
            // given
            Deck deck = Deck.create(generator);
            // when & then
            assertThatCode(deck::draw)
                .doesNotThrowAnyException();
        }

        @Test
        void null이_아닌_인스턴스를_반환한다() {
            // given
            Deck deck = Deck.create(generator);
            // when
            Card card = deck.draw();
            // then
            assertThat(card).isNotNull();
        }

        @Test
        void 남은_카드가_없다면_예외를_던진다() {
            // given
            Deck deck = Deck.create(emptyCardsGenerator);
            // when & then
            assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
        }
    }
}
