package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.LOSE;
import static blackjack.domain.game.GameOutcome.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.game.GameOutcome;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @Test
    @DisplayName("카드의 합의 반환한다.")
    void calculateScore() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TWO), Card.of(SPADE, THREE), Card.of(SPADE, TEN)));
        assertThat(cards.getScore()).isEqualTo(15);
    }

    @ParameterizedTest
    @DisplayName("카드의 합이 21이 보다 큰 지 반환한다.")
    @MethodSource("provideCardsAndExpectedBust")
    void isBust(final Cards cards, final boolean expected) {
        assertThat(cards.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedBust() {
        return Stream.of(
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN), Card.of(SPADE, TWO))),
                        true),
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN))),
                        false)
        );
    }

    @Test
    @DisplayName("생성 시 카드에는 null이 들어올 경우 예외를 발생해야 한다.")
    void createNullException() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("카드에는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("카드들을 반환한다.")
    void getValues() {
        final List<Card> initialCards = Arrays.asList(Card.of(SPADE, TWO), Card.of(SPADE, THREE), Card.of(SPADE, TEN));
        final Cards cards = new Cards(initialCards);
        assertThat(cards.values()).isEqualTo(initialCards);
    }

    @ParameterizedTest
    @DisplayName("다른 카드를 받아 자신의 합을 기준으로 승패를 반환한다.")
    @MethodSource("provideCardsAndExpectedHigher")
    void isHigherThan(final Cards another, final GameOutcome expected) {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, SEVEN)));
        assertThat(cards.isHigherThan(another)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedHigher() {
        return Stream.of(
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN))), LOSE),
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, FIVE))), WIN),
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, SEVEN))), DRAW)
        );
    }

    @Test
    @DisplayName("첫번째 카드를 반환한다.")
    void firstCard() {
        final Card firstCard = Card.of(SPADE, KING);
        final Cards cards = new Cards(Arrays.asList(firstCard, Card.of(SPADE, SEVEN)));
        assertThat(cards.firstCard()).isEqualTo(Collections.singletonList(firstCard));
    }

    @ParameterizedTest
    @DisplayName("합이 21인 두장의 카드로 이뤄져있는지 반환한다.")
    @MethodSource("provideCardsAndExpectedBlackJack")
    void isBlackJack(Cards cards, boolean expected) {
        assertThat(cards.isBlackJack()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedBlackJack() {
        return Stream.of(
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, A), Card.of(SPADE, KING))), true),
                Arguments.of(new Cards(Arrays.asList(Card.of(SPADE, A), Card.of(SPADE, NINE))), false)
        );
    }
}
