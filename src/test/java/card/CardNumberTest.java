package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    @DisplayName("가져올 수 있는 범위의 숫자가 아닌 경우 에러를 던진다.")
    void overCardNumberScoreRangeTest(int overScoreRangeIndex) {
        Assertions.assertThatThrownBy(() -> CardNumber.EIGHT.getCardNumber(overScoreRangeIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가능한 범위의 점수 값이 아닙니다.");
    }

}