package batting;

import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AmountTest {
    @Test
    @DisplayName("숫자를 입력받아 인스턴스를 생성한다.")
    void create() {
        assertThatCode(() -> new Amount(100))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력받은 숫자가 음수인 경우 예외를 던진다.")
    void createFailNegative() {
        assertThatThrownBy(() -> new Amount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100이상의 정수만 입력 가능합니다.");
    }

    @Test
    @DisplayName("입력받은 숫자가 100단위가 아닌 경우 예외를 던진다.")
    void createAmountUnit() {
        assertThatThrownBy(() -> new Amount(120))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100원 단위로 입력 가능합니다.");
    }

    @Nested
    @DisplayName("Result에 따라 최종금액을 계산한다.")
    class calculateRewardByResult {
        @Test
        @DisplayName("이긴 경우 원래금액의 2배를 반환한다.")
        void win() {
            Amount amount = new Amount(1000);

            assertThat(amount.calculateRewardByResult(WIN)).isEqualTo(2000);
        }

        @Test
        @DisplayName("진경우 원래금액의 -2배를 반환한다")
        void lose() {
            Amount amount = new Amount(1000);

            assertThat(amount.calculateRewardByResult(LOSE)).isEqualTo(-2000);
        }

        @Test
        @DisplayName("비긴 경우 원래금액을 반환한다.")
        void tie() {
            Amount amount = new Amount(1000);

            assertThat(amount.calculateRewardByResult(TIE)).isEqualTo(1000);
        }
    }
}
