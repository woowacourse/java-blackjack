package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import static domain.card.CardFixture.cardOf;
import static domain.card.CardRank.ACE;
import static domain.card.CardRank.FIVE;
import static domain.card.CardRank.FOUR;
import static domain.card.CardRank.THREE;
import static domain.card.CardSuit.SPADE;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("카드를 맨 앞에서 한 장 뽑을 수 있다.")
    @Test
    void draw() {
        Cards cards = Cards.from(List.of(cardOf(FIVE), cardOf(FOUR)));
        assertThat(cards.draw()).isEqualTo(new Card(SPADE, FIVE));
    }

    static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.of(Cards.from(List.of(cardOf(THREE))), false),
                Arguments.of(Cards.from(List.of(cardOf(ACE))), true)
        );
    }

    @DisplayName("에이스가 포함되었는지 알 수 있다.")
    @MethodSource
    @ParameterizedTest
    void hasAce(Cards cards, boolean expected) {
        assertThat(cards.hasAce()).isEqualTo(expected);
    }

    static Stream<Arguments> score() {
        return Stream.of(
                Arguments.of(Cards.from(List.of(cardOf(THREE))), false, 3),
                Arguments.of(Cards.from(List.of(cardOf(ACE))), false, 1),
                Arguments.of(Cards.from(List.of(cardOf(ACE))), true, 11)
        );
    }

    @DisplayName("점수를 계산할 수 있다.")
    @MethodSource
    @ParameterizedTest
    void score(Cards cards, boolean isSoft, int expected) {
        assertThat(cards.score(isSoft)).isEqualTo(expected);
    }

    @DisplayName("카드가 비어 있는 경우 예외를 발생한다.")
    @Test
    void emptyCards() {
        Cards cards = Cards.emptyCards();
        assertAll(
                () -> assertThrowsWithMessage(cards::draw, IllegalStateException.class, "카드가 없습니다. 카드를 추가하세요."),
                () -> assertThrowsWithMessage(cards::peek, IllegalStateException.class, "카드가 없습니다. 카드를 추가하세요."),
                () -> assertThrowsWithMessage(cards::shuffle, IllegalStateException.class, "카드가 없습니다. 카드를 추가하세요."),
                () -> assertThrowsWithMessage(cards::hasAce, IllegalStateException.class, "카드가 없습니다. 카드를 추가하세요."),
                () -> assertThrowsWithMessage(() -> cards.score(false), IllegalStateException.class, "카드가 없습니다. 카드를 추가하세요."),
                () -> assertThrowsWithMessage(() -> cards.score(true), IllegalStateException.class, "카드가 없습니다. 카드를 추가하세요.")
        );
    }

    private void assertThrowsWithMessage(ThrowingCallable executable, Class<? extends Throwable> expectedType, String expectedMessage) {
        assertThatThrownBy(executable)
                .isInstanceOf(expectedType)
                .hasMessage(expectedMessage);
    }
}
