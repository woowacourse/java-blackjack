package model.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.judgement.Profit;
import model.paticipant.BetAmount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1000001})
    void 베팅_금액이_1원미만이거나_100만원을_초과한다면_예외가_발생한다(int invalidAmount) {
        // when & then
        assertThatThrownBy(() -> new BetAmount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 1000000})
    void 베팅_금액이_1원부터_100만원이라면_올바른_베팅_금액이다(int validAmount) {
        // when
        BetAmount betAmount = new BetAmount(validAmount);

        // then
        assertThat(betAmount.amount()).isEqualTo(validAmount);
    }

    @Test
    void 베팅_금액을_수익으로_변환할_수_있다() {
        // given
        BetAmount betAmount = new BetAmount(1000);

        // when
        Profit profit = betAmount.toProfit();

        // then
        assertThat(profit).isEqualTo(new Profit(1000));
    }


    @Test
    void 베팅_금액을_손실로_변환할_수_있다() {
        // given
        BetAmount betAmount = new BetAmount(1000);

        // when
        Profit profit = betAmount.toNegativeProfit();

        // then
        assertThat(profit).isEqualTo(new Profit(-1000));
    }

    @Test
    void 블랙잭일_경우_베팅_금액의_1점_5배를_수익으로_변환한다() {
        // given
        BetAmount betAmount = new BetAmount(10000);

        // when
        Profit profit = betAmount.toBlackjackProfit();

        // then
        assertThat(profit).isEqualTo(new Profit(15000));
    }
}