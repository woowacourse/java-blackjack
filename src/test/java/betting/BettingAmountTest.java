package betting;

import static blackjackgame.Result.BLACKJACK;
import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @Test
    @DisplayName("숫자를 입력받아 인스턴스를 생성한다.")
    void create() {
        assertThatCode(() -> new BettingAmount(100))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "입력받은 숫자가 100미만 100000초과인 경우 예외를 던진다. 입력값 = {0}")
    @ValueSource(ints = {-100, 0, 90, 110000})
    void createFailNegative(int amount) {
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100 이상 100,000 이하의 정수만 입력 가능합니다.");
    }

    @Test
    @DisplayName("입력받은 숫자가 100단위가 아닌 경우 예외를 던진다.")
    void createAmountUnit() {
        assertThatThrownBy(() -> new BettingAmount(120))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100원 단위로 입력 가능합니다.");
    }

    @Nested
    @DisplayName("Result에 따라 최종금액을 계산한다.")
    class CalculateRewardByResult {
        @Test
        @DisplayName("이긴 경우 원래금액의 1배를 반환한다.")
        void win() {
            BettingAmount bettingAmount = new BettingAmount(1000);

            assertThat(bettingAmount.calculateRewardByResult(WIN)).isEqualTo(1000);
        }

        @Test
        @DisplayName("진경우 원래금액의 -1배를 반환한다")
        void lose() {
            BettingAmount bettingAmount = new BettingAmount(1000);

            assertThat(bettingAmount.calculateRewardByResult(LOSE)).isEqualTo(-1000);
        }

        @Test
        @DisplayName("비긴 경우 0을 반환한다.")
        void tie() {
            BettingAmount bettingAmount = new BettingAmount(1000);

            assertThat(bettingAmount.calculateRewardByResult(TIE)).isEqualTo(0);
        }

        @Test
        @DisplayName("블랙잭으로 이긴경우 1.5배를 반환한다")
        void blackjack() {
            BettingAmount bettingAmount = new BettingAmount(1000);

            assertThat(bettingAmount.calculateRewardByResult(BLACKJACK)).isEqualTo(1500);
        }
    }
}
