package blackjack.user.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {

    @Nested
    @DisplayName("배팅 금액 초기화 테스트")
    class CreateAmountTest {

        @Test
        @DisplayName("배팅금이 없다면 만원으로 원금을 초기화한다.")
        void createAmount() {
            BetAmount betAmount = BetAmount.initAmount();

            assertAll(() -> {
                assertThat(betAmount.getPrincipal()).isEqualTo(10000);
                assertThat(betAmount.getProfit()).isEqualTo(0);
            });
        }
    }

    @Nested
    @DisplayName("배팅 금액 입력 테스트")
    class CreateBetAmountTest {

        @ParameterizedTest
        @ValueSource(ints = {10000, 10001, 9999999, 10000000})
        @DisplayName("원금은 1만원에서 1000만원까지 입력할 수 있다.")
        void createWallet(int principal) {
            BetAmount betAmount = BetAmount.initAmountWithPrincipal(principal);

            assertThat(betAmount).isInstanceOf(BetAmount.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 999, 10000001})
        @DisplayName("원금이 1만원미만 1000만원초과라면 입력할 수 없다.")
        void notCreateWallet(int principal) {
            assertThatThrownBy(() -> BetAmount.initAmountWithPrincipal(principal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }
}
