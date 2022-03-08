package blackjack.domain;

import static blackjack.domain.CardNumber.*;
import static blackjack.domain.CardSymbol.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    @DisplayName("여러장의 카드가 주어지면 객체를 생성한다.")
    void createCards() {
        // given
        final Cards cards = new Cards(Set.of(new Card(DIAMOND, ACE),
                new Card(DIAMOND, TEN)));

        // when
        int actual = cards.getSize();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("모든 카드의 값을 합산한 값을 반환한다.")
    void sum(Set<Card> value, int expected) {
        // give
        final Cards cards = new Cards(value);

        // when
        int actual = cards.sum();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(Set.of(new Card(DIAMOND, ACE), new Card(DIAMOND, TEN)), 21),
                Arguments.of(Set.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE)), 5),
                Arguments.of(Set.of(new Card(DIAMOND, QUEEN), new Card(DIAMOND, JACK)), 20)
        );
    }

    @Test
    @DisplayName("카드를 추가한다.")
    void add() {
        // give
        final Cards cards = new Cards(Set.of(new Card(DIAMOND, ACE)));

        // when
        cards.add(new Card(DIAMOND, TEN));
        int actual = cards.getSize();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("합산한 값이 21을 초과하면 BUST를 반환한다.")
    void isBust1() {
        // give
        final Cards cards = new Cards(Set.of(new Card(DIAMOND, ACE), new Card(DIAMOND, TEN), new Card(DIAMOND, JACK)));

        // when
        final Status actual = cards.isBust();

        // then
        assertThat(actual).isEqualTo(Status.BUST);
    }

    @Test
    @DisplayName("합산한 값이 21을 초과하지 않으면 NOT_BUST를 반환한다.")
    void isBust2() {
        // give
        final Cards cards = new Cards(Set.of(new Card(DIAMOND, ACE), new Card(DIAMOND, TEN)));

        // when
        final Status actual = cards.isBust();

        // then
        assertThat(actual).isEqualTo(Status.NOT_BUST);
    }
}