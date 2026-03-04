import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DeckTest {
    @Nested
    @DisplayName("constructor(): ")
    class Constructor {
        @Test
        @DisplayName("[예외] - 사이즈가 52개인지 확인한다.")
        void invalidSize() {
            List<Card> cards = TestDefaults.createCards();
            cards.add(new Card(Rank.ACE, Suit.SPADE));
            assertThatThrownBy(() -> new Deck(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DECK_SIZE.getMessage());
        }

        @Test
        @DisplayName("[예외] - 카드가 중복되는지 확인한다.")
        void duplicate() {
            List<Card> cards = TestDefaults.createCards();

            cards.removeLast();
            cards.add(cards.getFirst());    //중복 발생시키기

            assertThatThrownBy(() -> new Deck(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DECK_DUPLICATE.getMessage());
        }

    }

    @Nested
    @DisplayName("passCard(): ")
    class PassCard {

        public static Stream<Arguments> drawCard() {
            return Stream.of(
                    Arguments.of(0, TestDefaults.getCard(0)),
                    Arguments.of(2, TestDefaults.getCard(2)),
                    Arguments.of(51, TestDefaults.getCard(51))
            );
        }

        @DisplayName("인덱스에 맞는 카드를 나눠준다.")
        @ParameterizedTest
        @MethodSource
        void drawCard(int idx, Card expected) {
            Deck deck = TestDefaults.createDeck();

            assertThat(deck.drawCard(idx)).isEqualTo(expected);
            assertThat(deck.contains(expected)).isFalse();
        }

        @DisplayName("[예외] - cards의 사이즈 이상의 값이 들어오면 예외를 반환한다.")
        @ParameterizedTest
        @CsvSource({
                "-1",
                "53"
        })
        void invalidSize(int idx) {
            Deck deck = TestDefaults.createDeck();
            assertThatThrownBy(() -> deck.drawCard(idx))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INDEX_RANGE.getMessage());
        }
    }


}
