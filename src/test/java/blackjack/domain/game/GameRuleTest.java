package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameRuleTest {


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
    @DisplayName("버스트된 카드점수를 0으로 계산할 수 있다.")
    @CsvSource({"20,20", "21,21", "22,0"})
    void canApplyBustToPoint(int point, int expectedPoint) {
        int actualPoint = GameRule.applyBustToPoint(point);
        assertThat(actualPoint).isEqualTo(expectedPoint);
    }
}