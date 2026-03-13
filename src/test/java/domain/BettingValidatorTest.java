package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.mesage.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BettingValidatorTest {

    @Test
    @DisplayName("사용자 배팅금액이 0이하의 정수일 경우 실패")
    void 사용자_배팅금액_0이하의_정수_입력_실패_테스트() {
        String input = "0";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> BettingValidator.validateBetAmount(input))
                .withMessage(ErrorMessage.INVALID_BET_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("사용자 배팅금액이 문자일 경우 실패")
    void 사용자_배팅금액_문자_입력_실패_테스트() {
        String input = "fail";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> BettingValidator.validateBetAmount(input))
                .withMessage(ErrorMessage.INVALID_BET_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("사용자 배팅금액이 null일 경우 실패")
    void 사용자_배팅금액이_null일때_실패_테스트() {
        String input = null;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> BettingValidator.validateBetAmount(input))
                .withMessage(ErrorMessage.EMPTY_BET_AMOUNT_INPUT.getMessage());
    }
}