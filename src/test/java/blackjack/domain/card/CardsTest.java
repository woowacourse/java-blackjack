package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static blackjack.domain.card.CardSymbol.HEART;
import static blackjack.domain.card.CardSymbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @CsvSource(value = {"SEVEN:true", "FOUR:false"}, delimiter = ':')
    @DisplayName("BUST인지 확인한다.")
    void isBust1(CardNumber cardNumber, boolean expected) {
        // give
        final Cards cards = new Cards(Set.of(Card.of(DIAMOND, JACK), Card.of(DIAMOND, FIVE),
                Card.of(DIAMOND, cardNumber)));

        // when
        final boolean actual = cards.isBust();

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

    @Test
    @DisplayName("처음으로 추가된 카드를 찾는다.")
    void findFirst() {
        // give
        final Cards cards = new Cards();
        final Card firstCard = Card.of(CLUB, KING);

        cards.add(firstCard);
        cards.add(Card.of(CLUB, QUEEN));

        // when
        final Card actual = cards.findFirst();

        // then
        assertThat(actual).isEqualTo(firstCard);
    }

    @Test
    @DisplayName("카드가 없으면 예외를 던진다.")
    void findFirst_exception() {
        // give
        final Cards cards = new Cards();

        // when
        // then
        assertThatThrownBy(cards::findFirst)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드가 한 장도 없습니다.");
    }
}