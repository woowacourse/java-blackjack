package blackjack.domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardTest {

    @DisplayName("문자와 모양을 가지고 있다.")
    @Test
    void create_success() {
        assertThatCode(() -> Card.of(Rank.FOUR, Suit.DIAMONDS))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자로 계산한 값을 반환한다.")
    @ParameterizedTest
    @MethodSource("createCard")
    void getValue(Card card, int expected) {
        int actual = card.getRankValue();

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> createCard() {
        return Stream.of(
                Arguments.of(TWO_HEART, 2),
                Arguments.of(TEN_HEART, 10),
                Arguments.of(ACE_HEART, 11)
        );
    }

    @DisplayName("현재 카드가 Ace인지 판단한다.")
    @Test
    void isAceCard() {
        boolean isAce = ACE_HEART.isAceCard();
        boolean isNotAce = TEN_HEART.isAceCard();

        Assertions.assertAll(
                () -> assertThat(isAce).isTrue(),
                () -> assertThat(isNotAce).isFalse()
        );
    }
}
