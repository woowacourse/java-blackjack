package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Bets;
import domain.model.Player;
import domain.model.PlayerStatus;
import org.junit.jupiter.api.Test;

public class BetTest {

    @Test
    void 플레이어의_베팅_금액_초기() {
        // given
        Player phobi = Player.of("phobi");

        // when
        Bets bets = new Bets();
        bets.addBet(phobi, 10000);

        // then
        assertThat(bets.getBetAmount(phobi)).isEqualTo(10000);
    }

    @Test
    void 플레이어_승리_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        Bets bets = new Bets();
        bets.addBet(phobi, 10000);
        phobi.changeStatus(PlayerStatus.WIN);

        // when
        int finalMoney = bets.getFinalMoney(phobi);

        // then
        assertThat(finalMoney).isEqualTo(10000);
    }

    @Test
    void 플레이어_패배_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        Bets bets = new Bets();
        bets.addBet(phobi, 10000);
        phobi.changeStatus(PlayerStatus.LOSS);

        // when
        int finalMoney = bets.getFinalMoney(phobi);

        // then
        assertThat(finalMoney).isEqualTo(-10000);
    }

    @Test
    void 플레이어_블랙잭_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        Bets bets = new Bets();
        bets.addBet(phobi, 10000);
        phobi.changeStatus(PlayerStatus.BLACK_JACK);

        // when
        int finalMoney = bets.getFinalMoney(phobi);

        // then
        assertThat(finalMoney).isEqualTo(15000);
    }

    @Test
    void 플레이어_무승부_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        Bets bets = new Bets();
        bets.addBet(phobi, 10000);
        phobi.changeStatus(PlayerStatus.DRAW);

        // when
        int finalMoney = bets.getFinalMoney(phobi);

        // then
        assertThat(finalMoney).isEqualTo(0);
    }
}
