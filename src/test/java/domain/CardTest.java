package domain;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    static Stream<Arguments> createCard() {
        return Stream.of(
                Arguments.of(
                        Map.entry(TWO_HEARTS, 2),
                        Map.entry(TEN_HEARTS, 10),
                        Map.entry(ACE_HEARTS, 11)
                )
        );
    }

    @DisplayName("문자(String)와 모양(String)을 가지고 있다.")
    @Test
    void create_success() {
        assertThatCode(() -> new Card(Rank.FOUR, Suit.DIAMONDS))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자로 계산한 값을 반환한다.")
    @ParameterizedTest
    @MethodSource("createCard")
    void getValue(Map.Entry<Card, Integer> input) {
        int actual = input.getKey().getRankValue();
        int expected = input.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("현재 카드가 Ace인지 판단한다.")
    @Test
    void isAceCard() {
        boolean isAce = ACE_HEARTS.isAceCard();
        boolean isNotAce = TEN_HEARTS.isAceCard();

        Assertions.assertAll(
                () -> assertThat(isAce).isTrue(),
                () -> assertThat(isNotAce).isFalse()
        );
    }
}
