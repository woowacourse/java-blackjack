package blackjack.domain.card;

import static blackjack.domain.Fixtures.ACE_DIAMOND;
import static blackjack.domain.Fixtures.ACE_HEART;
import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static blackjack.domain.Fixtures.TWO_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    @DisplayName("카드 리스트에 카드를 추가한다.")
    void addCard() {
        final Cards cards = new Cards(ACE_DIAMOND, JACK_DIAMOND);
        final int expected = 3;

        cards.addCard(TWO_DIAMOND);
        final int actual = cards.getCards().size();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "카드의 처음 받은 카드인지 여부는 {1}이다.")
    @MethodSource("provideCardsAndExpectedForInitialCards")
    @DisplayName("처음받은 카드인지(2장인지) 확인한다.")
    void isInitialCards(final Cards cards, final boolean expected) {
        final boolean actual = cards.isInitialCards();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedForInitialCards() {
        return Stream.of(
                Arguments.of(new Cards(ACE_DIAMOND, JACK_DIAMOND), true),
                Arguments.of(new Cards(ACE_DIAMOND, ACE_HEART), true),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, TWO_DIAMOND), false),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, JACK_DIAMOND, TWO_DIAMOND), false)
        );
    }

    @ParameterizedTest(name = "카드의 블랙잭 여부는 {1}이다.")
    @MethodSource("provideCardsAndExpectedForBlackJack")
    @DisplayName("블랙잭인지 확인한다.")
    void isBlackJack(final Cards cards, final boolean expected) {
        final boolean actual = cards.isBlackJack();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedForBlackJack() {
        return Stream.of(
                Arguments.of(new Cards(ACE_DIAMOND, JACK_DIAMOND), true),
                Arguments.of(new Cards(ACE_DIAMOND, ACE_HEART), false),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, TWO_DIAMOND), false),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, JACK_DIAMOND, TWO_DIAMOND), false)
        );
    }

    @ParameterizedTest(name = "카드의 총합이 21을 넘었는지의 여부는 {1}이다.")
    @MethodSource("provideCardsAndExpectedForBust")
    @DisplayName("카드의 총합이 21을 넘었는지 확인한다.")
    void isBust(final Cards cards, final boolean expected) {
        final boolean actual = cards.isBust();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedForBust() {
        return Stream.of(
                Arguments.of(new Cards(ACE_DIAMOND, JACK_DIAMOND), false),
                Arguments.of(new Cards(ACE_DIAMOND, ACE_HEART), false),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, TWO_DIAMOND), false),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, JACK_DIAMOND, TWO_DIAMOND), true)
        );
    }

    @Test
    @DisplayName("Ace를 포함하지 않는 카드의 총합을 구한다.")
    void calculateScore() {
        final Cards cards = new Cards(KING_DIAMOND, JACK_DIAMOND);
        final int expected = 20;

        final int actual = cards.calculateScore(cards.sum(), cards.getCountOfAce());
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "카드의 총합은 {1}이다.")
    @MethodSource("provideCardsAndScore")
    @DisplayName("Ace를 포함한 카드의 총합을 구한다.")
    void calculateScoreIncludingAce(final Cards cards, final int expected) {
        final int actual = cards.calculateScore(cards.sum(), cards.getCountOfAce());
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(new Cards(ACE_DIAMOND, JACK_DIAMOND), 21),
                Arguments.of(new Cards(ACE_DIAMOND, ACE_HEART), 12),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, TWO_DIAMOND), 13),
                Arguments.of(new Cards(ACE_DIAMOND, KING_DIAMOND, JACK_DIAMOND, TWO_DIAMOND), 23)
        );
    }
}
