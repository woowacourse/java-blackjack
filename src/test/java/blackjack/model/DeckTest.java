package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    final CardsGenerator generator = new UniqueCardsGenerator();
    final CardsGenerator emptyCardsGenerator = List::of;

    @Test
    @DisplayName("CardsGenerator를 통해 인스턴스를 생성한다.")
    void createInstanceByCardsGenerator() {
        assertThatCode(() -> Deck.shuffled(generator))
                .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("카드 인스턴스 하나를 반환한다")
    class ReturnCard {
        @Test
        @DisplayName("남은 카드가 있다면 예외가 발생하지 않는다")
        void returnCard() {
            // given
            Deck deck = Deck.shuffled(generator);

            // when & then
            assertThatCode(deck::draw)
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("null이 아닌 인스턴스를 반환한다")
        void returnNotNullCard() {
            // given
            Deck deck = Deck.shuffled(generator);

            // when
            Card card = deck.draw();

            // then
            assertThat(card).isNotNull();
        }

        @Test
        @DisplayName("남은 카드가 없다면 예외를 던진다")
        void throwExceptionIfCardsEmpty() {
            // given
            Deck deck = Deck.shuffled(emptyCardsGenerator);

            // when & then
            assertThatThrownBy(deck::draw)
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}
