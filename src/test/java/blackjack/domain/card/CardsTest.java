package blackjack.domain.card;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    void 생성_시_null이_들어오는_경우_예외발생() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }

    @Test
    void 생성_시_cards크기가_2미만인_경우_예외발생() {
        assertThatThrownBy(() -> new Cards(List.of(new Card(SPADES, A))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("cards는 2장이상이 들어와야 합니다.");
    }

    @ParameterizedTest
    @MethodSource("generateIsBustValues")
    void 카드상태가_버스트인지_확인(final List<Card> inputCards, final boolean expected) {
        final Cards cards = new Cards(inputCards);
        assertThat(cards.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> generateIsBustValues() {
        return Stream.of(
                Arguments.of(List.of(new Card(SPADES, KING), new Card(SPADES, SEVEN), new Card(SPADES, TEN)), true),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, TEN)), false),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, EIGHT)), false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateIsBlackjackValues")
    void 카드상태가_블랙잭인지_확인(final List<Card> inputCards, final boolean expected) {
        final Cards cards = new Cards(inputCards);
        assertThat(cards.isBlackjack()).isEqualTo(expected);
    }

    private static Stream<Arguments> generateIsBlackjackValues() {
        return Stream.of(
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, TEN)), true),
                Arguments.of(List.of(new Card(SPADES, KING), new Card(SPADES, SEVEN), new Card(SPADES, FOUR)), false),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, EIGHT)), false)
        );
    }

    @Test
    void 최대_스코어가_버스트인지_확인() {
        final Cards cards = new Cards(List.of(new Card(SPADES, SEVEN), new Card(SPADES, EIGHT), new Card(SPADES, A)));
        assertThat(cards.isMaxScoreBust()).isTrue();
    }
}
