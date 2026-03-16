package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import view.mesage.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "0.1"})
    @DisplayName("사용자 배팅금액이 0이하의 정수일 경우 실패")
    void 사용자_배팅금액_0이하의_정수_입력_실패_테스트(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BetAmount.of(input))
                .withMessage(ErrorMessage.INVALID_BET_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("사용자 배팅금액이 문자일 경우 실패")
    void 사용자_배팅금액_문자_입력_실패_테스트() {
        String input = "fail";

        assertThatIllegalArgumentException()
                .isThrownBy(() ->  BetAmount.of(input))
                .withMessage(ErrorMessage.INVALID_BET_AMOUNT.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("사용자 배팅금액이 null 또는 empty일 경우 실패")
    void 사용자_배팅금액이_null_or_empty일때_실패_테스트(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() ->  BetAmount.of(input))
                .withMessage(ErrorMessage.EMPTY_BET_AMOUNT_INPUT.getMessage());
    }

    @Test
    @DisplayName("사용자 배팅금액 검증 성공")
    void 사용자_배팅금액_입력_성공_테스트() {
        String input = "100";

        BetAmount betAmount = BetAmount.of(input);

        assertThat(betAmount.getBetAmount()).isEqualTo(100L);
    }
}