package blackjack.domain.game;

import static blackjack.domain.game.GameRule.BUST_THRESHOLD;
import static blackjack.domain.game.GameRule.INITIAL_CARD_COUNT;
import static blackjack.domain.game.GameRule.isBlackJack;
import static blackjack.domain.game.GameRule.isBust;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class GameRuleTest {

    @ParameterizedTest
    @DisplayName("버스트 여부를 확인할 수 있다.")
    @CsvSource({"20,false", "21,false", "22,true"})
    void canCheckBust(int point, boolean expectedIsBust) {
        boolean actualIsBust = isBust(point);
        assertThat(actualIsBust).isEqualTo(expectedIsBust);
    }

    @ParameterizedTest
    @DisplayName("블랙잭인지 확인할 수 있다.")
    @MethodSource()
    void canCheckBlackjack(int point, int cardCount, boolean expectedIsBlackjack) {
        boolean actualIsBlackjack = isBlackJack(cardCount, point);
        assertThat(actualIsBlackjack).isEqualTo(expectedIsBlackjack);
    }

    static Stream<Arguments> canCheckBlackjack() {
        return Stream.of(
                Arguments.of(BUST_THRESHOLD.getValue() - 1, INITIAL_CARD_COUNT.getValue(), false),
                Arguments.of(BUST_THRESHOLD.getValue(), INITIAL_CARD_COUNT.getValue(), true),
                Arguments.of(BUST_THRESHOLD.getValue() + 1, INITIAL_CARD_COUNT.getValue(), false),
                Arguments.of(BUST_THRESHOLD.getValue() - 1, INITIAL_CARD_COUNT.getValue() + 1, false),
                Arguments.of(BUST_THRESHOLD.getValue(), INITIAL_CARD_COUNT.getValue() + 1, false),
                Arguments.of(BUST_THRESHOLD.getValue() + 1, INITIAL_CARD_COUNT.getValue() + 1, false)
        );
    }
}