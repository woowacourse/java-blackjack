package team.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BetTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("pobi", 10000);
        dealer = new Dealer();
    }

    @Test
    void 플레이어가_패배하는_경우_반환금액은_0원이_된다() {
        int payout = player.getPayout(Result.LOSE);

        assertThat(payout).isEqualTo(0);
    }

    @Test
    void 플레이어가_승리하는_경우_반환금액은_배팅금액의_2배가_된다() {
        int payout = player.getPayout(Result.WIN);

        assertThat(payout).isEqualTo(20000);
    }

    @Test
    void 플레이어가_블랙잭으로_승리한_경우_반환금액은_배팅금액의_2_5배가_된다() {
        int payout = player.getPayout(Result.BLACKJACK);

        assertThat(payout).isEqualTo(25000);
    }

    /**
     * 첨언. 둘다 블랙잭으로 무승부인 경우도 해당 케이스에 포함된다.
     * 둘 다 블랙잭으로 Result.DRAW 판별을 하는 책임은 BlackjackRule이 갖는다.
     * 이에 따라 해당 테스트도 BlackjackRule 테스트에 작성한다.
     */
    @Test
    void 플레이어가_무승부인_경우_배팅금액을_그대로_돌려받는다() {
        int payout = player.getPayout(Result.DRAW);

        assertThat(payout).isEqualTo(10000);
    }
}
