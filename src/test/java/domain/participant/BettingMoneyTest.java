package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @Nested
    class ValidCases {

        @DisplayName("배팅 금액은 0 이상이어야 한다.")
        @Test
        void calculateRevenueAmount() {
            double multiple = 1.5;
            int amount = 1000;
            BettingMoney bettingMoney = new BettingMoney(amount);

            // when
            int revenueAmount = bettingMoney.calculateRevenueAmount(multiple);

            // then
            assertThat(revenueAmount).isEqualTo((int) (amount * multiple));
        }
    }

    @Nested
    class InvalidCases {

        @DisplayName("배팅 금액은 0 이상이어야 한다.")
        @ParameterizedTest
        @ValueSource(ints = {-1, -100, -1000})
        void validatePositive(int negativeAmount) {
            // when & then
            assertThatThrownBy(() -> new BettingMoney(negativeAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("배팅 금액은 0 이상이어야 합니다.");
        }

        @DisplayName("배팅 금액은 일정 금액의 범위여야한다.")
        @ParameterizedTest
        @ValueSource(ints = {BettingMoney.MIN_AMOUNT - 1, BettingMoney.MAX_AMOUNT + 1})
        void validateRange(int nonRangeAmount) {
            // when & then
            assertThatThrownBy(() -> new BettingMoney(nonRangeAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("배팅 금액은 최소 " + BettingMoney.MIN_AMOUNT + "원 이상, 최대 " + BettingMoney.MAX_AMOUNT
                            + "원 이하여야 합니다.");
        }

        @DisplayName("배팅 금액은 일정 금액의 배수여야 한다.")
        @ParameterizedTest
        @ValueSource(ints = {BettingMoney.UNIT_AMOUNT * 10 - 1, BettingMoney.UNIT_AMOUNT * 10 + 1})
        void validateMultiple(int nonMultipleAmount) {
            // when & then
            assertThatThrownBy(() -> new BettingMoney(nonMultipleAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("배팅 금액은 " + BettingMoney.UNIT_AMOUNT + "원 단위여야 합니다.");
        }
    }
}
