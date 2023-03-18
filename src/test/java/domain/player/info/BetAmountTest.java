package domain.player.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetAmountTest {

    @Test
    @DisplayName("1000원 이하를 베팅하면 예외가 발생한다.")
    void givenUnderThousand_thenFail() {
        assertThatThrownBy(() -> BetAmount.from(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000 이상만 베팅해주세요.");
    }

    @Test
    @DisplayName("100원 단위 이하로 베팅하면 예외가 발생한다.")
    void givenUnderHundredUnit_thenFail() {
        assertThatThrownBy(() -> BetAmount.from(1001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("100 단위만 입력해주세요.");
    }

    @Test
    @DisplayName("false를 입력하면 베팅한 금액 그대로 반환한다")
    void givenFalse_thenReturnBetAmount() {
        //given
        final int amount = 1000;
        final BetAmount betAmount = BetAmount.from(amount);

        //when
        final int winBet = betAmount.winBet(false);

        //then
        assertThat(winBet).isEqualTo(amount);
    }

    @Test
    @DisplayName("true를 입력하면 베팅한 금액의 1.5배를 반환한다")
    void givenTrue_thenReturnBetAmountOneHalf() {
        //given
        final int amount = 1000;
        final BetAmount betAmount = BetAmount.from(amount);

        //when
        final int winBet = betAmount.winBet(true);

        //then
        assertThat(winBet).isEqualTo((int) (amount * 1.5));
    }

    @Test
    void loseBetTest() {
        //given
        final int amount = 1000;
        final BetAmount betAmount = BetAmount.from(amount);

        //when
        final int loseBet = betAmount.loseBet();

        //then
        assertThat(loseBet).isEqualTo(amount * -1);
    }

    @Test
    void returnBetTest() {
        //given
        final int amount = 1000;
        final BetAmount betAmount = BetAmount.from(amount);

        //when
        final int returnBet = betAmount.returnBet();

        //then
        assertThat(returnBet).isEqualTo(0);
    }
}
