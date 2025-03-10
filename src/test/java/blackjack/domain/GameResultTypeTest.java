package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTypeTest {

    @DisplayName("값을 비교하면 적절한 값을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"20,1,WIN", "20,20,TIE", "1,20,LOSE"}, delimiter = ',')
    void test1(int value, int comparedValue, GameResultType expect) {
        // given & when
        GameResultType result = GameResultType.find(value, comparedValue);

        // then
        assertThat(result).isEqualTo(expect);
    }
}
