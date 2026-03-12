package domain.pariticipant;

import constant.BlackjackConstant;
import exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.BlackjackConstant.BETTING_LIMIT;
import static exception.ErrorMessage.BETTING_LIMIT_OUT_OF_BOUNDS_ERROR;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingAmountTest {


    @ParameterizedTest
    @ValueSource(longs = {1, BETTING_LIMIT})
    @DisplayName("베팅 금액은 0보다 크고 1,000,000 이하여야 한다.")
    public void createBettingAmount_success(long bettingAmount) {
        // when // then
        assertThatCode(() -> new BettingAmount(bettingAmount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(longs = {0, BETTING_LIMIT + 1})
    @DisplayName("베팅 금액은 0이하거나 1,000,000 보다 크면 예외가 발생한다.")
    public void createBettingAmount_fail(long bettingAmount) {
        // when // then
        assertThatThrownBy(() -> new BettingAmount(bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_LIMIT_OUT_OF_BOUNDS_ERROR.getMessage());
    }

}
