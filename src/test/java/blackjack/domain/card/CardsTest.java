package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.PlayStatus;

class CardsTest {

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("모든 카드의 값을 합산한 값을 반환한다.")
    void sum(Set<Card> value, int expected) {
        // give
        Cards cards = new Cards(value);

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
        Cards cards = new Cards(Set.of(new Card(DIAMOND, ACE)));

        // when
        cards.add(new Card(DIAMOND, TEN));
        int actual = cards.sum();

        // then
        assertThat(actual).isEqualTo(21);
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN:BUST", "FOUR:HIT"}, delimiter = ':')
    @DisplayName("합산한 값이 21을 초과하면 BUST를 반환한다.")
    void isBust1(CardNumber cardNumber, PlayStatus expected) {
        // give
        Cards cards = new Cards(Set.of(new Card(DIAMOND, JACK), new Card(DIAMOND, FIVE),
            new Card(DIAMOND, cardNumber)));

        // when
        PlayStatus actual = cards.getStatus();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSource")
    @DisplayName("BUST이고 ACE를 포함하면 ACE의 값을 1로 바꾼다.")
    void changeAceValue(Cards cards, int expected) {
        // when
        int actual = cards.sum();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSource() {
        return Stream.of(
            Arguments.of(new Cards(Set.of(new Card(DIAMOND, ACE),
                new Card(CLUB, TEN),
                new Card(CLUB, FIVE))), 16),
            Arguments.of(new Cards(Set.of(new Card(DIAMOND, ACE),
                new Card(CLUB, ACE),
                new Card(SPADE, ACE),
                new Card(HEART, ACE))), 14),
            Arguments.of(new Cards(Set.of(new Card(DIAMOND, ACE),
                new Card(CLUB, ACE),
                new Card(SPADE, ACE),
                new Card(HEART, TEN))), 13)
        );
    }
}