package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static blackjack.domain.card.CardSymbol.HEART;
import static blackjack.domain.card.CardSymbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("모든 카드의 값을 합산한 값을 반환한다.")
    void sum(Set<Card> value, int expected) {
        // give
        final Cards cards = new Cards(value);

        // when
        int actual = cards.sumValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(Set.of(Card.of(DIAMOND, ACE), Card.of(DIAMOND, TEN)), 21),
                Arguments.of(Set.of(Card.of(DIAMOND, TWO), Card.of(DIAMOND, THREE)), 5),
                Arguments.of(Set.of(Card.of(DIAMOND, QUEEN), Card.of(DIAMOND, JACK)), 20)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN:BUST", "FOUR:HIT"}, delimiter = ':')
    @DisplayName("합산한 값이 21을 초과하면 BUST를 반환한다.")
    void isBust1(CardNumber cardNumber, Status expected) {
        // give
        final Cards cards = new Cards(Set.of(Card.of(DIAMOND, JACK), Card.of(DIAMOND, FIVE),
                Card.of(DIAMOND, cardNumber)));

        // when
        final Status actual = cards.getStatus();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideSource")
    @DisplayName("BUST이고 ACE를 포함하면 ACE의 값을 1로 바꾼다.")
    void upgradeAceValue(Cards cards, int expected) {
        // when
        final int actual = cards.sumValue();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideSource() {
        return Stream.of(
                Arguments.of(new Cards(Set.of(Card.of(DIAMOND, ACE),
                        Card.of(CLUB, TEN),
                        Card.of(CLUB, FIVE))), 16),
                Arguments.of(new Cards(Set.of(Card.of(DIAMOND, ACE),
                        Card.of(CLUB, ACE),
                        Card.of(SPADE, ACE),
                        Card.of(HEART, ACE))), 14),
                Arguments.of(new Cards(Set.of(Card.of(DIAMOND, ACE),
                        Card.of(CLUB, ACE),
                        Card.of(SPADE, ACE),
                        Card.of(HEART, TEN))), 13)
        );
    }
}