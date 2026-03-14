package team.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BetTest {

    @Test
    void 플레이어가_패배하는_경우_수익_금액은_마이너스_원금이_된다() {
        Bet bet = Bet.from(10000);

        int payout = bet.calculatePayout(Result.LOSE);

        assertThat(payout).isEqualTo(-10000);
    }

    @Test
    void 플레이어가_승리하는_경우_수익_금액은_배팅금액이_된다() {
        Bet bet = Bet.from(10000);

        int payout = bet.calculatePayout(Result.WIN);

        assertThat(payout).isEqualTo(10000);
    }

    @Test
    void 플레이어가_블랙잭으로_승리한_경우_수익_금액은_배팅금액의_1_5배가_된다() {
        Bet bet = Bet.from(10000);

        int payout = bet.calculatePayout(Result.BLACKJACK);

        assertThat(payout).isEqualTo(15000);
    }

    /**
     * 첨언. 둘다 블랙잭으로 무승부인 경우도 해당 케이스에 포함된다. 둘 다 블랙잭으로 Result.DRAW 판별을 하는 책임은 BlackjackRule이 갖는다. 이에 따라 해당 테스트도
     * BlackjackRule 테스트에 작성한다.
     */
    @Test
    void 플레이어가_무승부인_경우_수익_금액은_없다() {
        Bet bet = Bet.from(10000);

        int payout = bet.calculatePayout(Result.DRAW);

        assertThat(payout).isEqualTo(0);
    }

}
