package blackjack.model.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    void 플레이어가_승리한_경우_수익률은_100퍼센트이다() {
        // given
        BetAmount betAmount = new BetAmount(10000);

        // when
        double amount = betAmount.calculateProfitAmount(ParticipantResult.WIN);

        // then
        Assertions.assertThat(amount).isEqualTo(10000);
    }

    @Test
    void 플레이어가_블랙잭인_경우_수익률은_150퍼센트이다() {
        // given
        BetAmount betAmount = new BetAmount(10000);

        // when
        double amount = betAmount.calculateProfitAmount(ParticipantResult.BLACKJACK);

        // then
        Assertions.assertThat(amount).isEqualTo(15000);
    }
}
