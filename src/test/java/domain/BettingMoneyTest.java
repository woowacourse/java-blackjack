package domain;

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
    }
}
