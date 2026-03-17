package domain.participant;

import static domain.participant.BetAmount.BET_UNIT;
import static domain.participant.BetAmount.MAXIMUM_BET_AMOUNT;
import static domain.participant.BetAmount.MINIMUM_BET_AMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    @DisplayName("베팅 금액은 BET_UNIT 단위에 맞게 떨어져야 한다.")
    public void 베팅_금액이_BET_UNIT_단위라면_성공() {
        // given
        final int betAmount = BET_UNIT * 100;

        // then
        assertThat(new BetAmount(betAmount)).isInstanceOf(BetAmount.class);
    }

    @Test
    @DisplayName("베팅 금액이 BET_UNIT 단위가 아니라면 에러가 발생한다.")
    public void 베팅_금액이_BET_UNIT_단위가_아니면_실패() {
        // given
        final int betAmount = (BET_UNIT - 1) * (BET_UNIT - 1);

        // then
        assertThatThrownBy(() -> new BetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("베팅 금액은 MINIMUM_BET_AMOUNT 이상, MAXIMUM_BET_AMOUNT 이하여야 한다.")
    public void 베팅_제한_범위_이내라면_성공() {
        // given
        final int littleAmount = MINIMUM_BET_AMOUNT + BET_UNIT;
        final int muchAmount = MAXIMUM_BET_AMOUNT - BET_UNIT;

        // then
        assertThat(new BetAmount(MINIMUM_BET_AMOUNT)).isInstanceOf(BetAmount.class);
        assertThat(new BetAmount(littleAmount)).isInstanceOf(BetAmount.class);
        assertThat(new BetAmount(muchAmount)).isInstanceOf(BetAmount.class);
        assertThat(new BetAmount(MAXIMUM_BET_AMOUNT)).isInstanceOf(BetAmount.class);
    }

    @Test
    @DisplayName("베팅 금액은 MINIMUM_BET_AMOUNT 미만, MAXIMUM_BET_AMOUNT 초과라면 에러가 발생한다.")
    public void 베팅_제한_범위를_벗어나면_실패() {
        // given
        final int tooLittle = MINIMUM_BET_AMOUNT - BET_UNIT;
        final int tooMuch = MAXIMUM_BET_AMOUNT + BET_UNIT;

        // then
        assertThatThrownBy(() -> new BetAmount(tooLittle))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BetAmount(tooMuch))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
