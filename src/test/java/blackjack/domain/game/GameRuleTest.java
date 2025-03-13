package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class GameRuleTest {

    @ParameterizedTest
    @DisplayName("히트 가능 여부를 확인할 수 있다.")
    @CsvSource({"19,true", "20,true", "21,false", "22,false"})
    void canCheckPossibilityOfHit(int point, boolean expectedIsPossible) {
        boolean actualIsPossible = GameRule.checkPossibilityOfHit(point);
        assertThat(actualIsPossible).isEqualTo(expectedIsPossible);
    }

    @ParameterizedTest
    @DisplayName("버스트 여부를 확인할 수 있다.")
    @CsvSource({"20,false", "21,false", "22,true"})
    void canCheckIsBust(int point, boolean expectedIsBust) {
        boolean actualIsBust = GameRule.isBust(point);
        assertThat(actualIsBust).isEqualTo(expectedIsBust);
    }

    @ParameterizedTest
    @DisplayName("달러의 카드 드로잉 가능여부를 확인할 수 있다.")
    @CsvSource({"15,true", "16,true", "17,false"})
    void canCheckPossibilityOfDealerDrawing(int point, boolean expectedIsPossible) {
        boolean actualIsPossible = GameRule.checkPossibilityOfDealerDrawing(point);
        assertThat(actualIsPossible).isEqualTo(expectedIsPossible);
    }

    @ParameterizedTest
    @DisplayName("블랙잭인지 확인할 수 있다.")
    @CsvSource({"20,false", "21,true", "22,false"})
    void canCheckBlackjack(int point, boolean expectedIsBlackjack) {
        boolean actualIsBlackjack = GameRule.checkBlackJack(point);
        assertThat(actualIsBlackjack).isEqualTo(expectedIsBlackjack);
    }

    @ParameterizedTest
    @DisplayName("초기 핸드인지 확인할 수 있다.")
    @MethodSource()
    void canCheckInitialHand(int cardCountInHand, boolean expectedIsInitial) {
        boolean actualIsInitial = GameRule.checkInitialHand(cardCountInHand);
        assertThat(actualIsInitial).isEqualTo(expectedIsInitial);
    }

    static Stream<Arguments> canCheckInitialHand() {
        return Stream.of(
                Arguments.of(GameRule.INITIAL_CARD_COUNT.getValue() - 1, false),
                Arguments.of(GameRule.INITIAL_CARD_COUNT.getValue(), true),
                Arguments.of(GameRule.INITIAL_CARD_COUNT.getValue() + 1, false)
        );
    }

    @ParameterizedTest
    @DisplayName("버스트된 카드점수를 0으로 계산할 수 있다.")
    @CsvSource({"20,20", "21,21", "22,0"})
    void canApplyBustToPoint(int point, int expectedPoint) {
        int actualPoint = GameRule.applyBustToPoint(point);
        assertThat(actualPoint).isEqualTo(expectedPoint);
    }
}