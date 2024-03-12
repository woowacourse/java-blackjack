package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @ParameterizedTest
    @ValueSource(ints = {1})
    @DisplayName("받은 position에 맞는 Score을 돌려준다.")
    void getCardNumberTest(int cardPosition) {
        Assertions.assertThat(CardNumber.ACE.getCardNumber(cardPosition)).isEqualTo(11);
    }

    @DisplayName("에이스 카드의 추가 점수를 계산한다.")
    @Test
    void calculateAdditionalAceCardScore() {
        Assertions.assertThat(CardNumber.calculatePlusAceCardScore()).isEqualTo(10);
    }
}
