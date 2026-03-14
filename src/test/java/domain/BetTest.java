package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Player;
import domain.model.PlayerStatus;
import org.junit.jupiter.api.Test;

public class BetTest {

    @Test
    void 플레이어의_베팅_금액_초기() {
        // given
        Player phobi = Player.of("phobi");

        // when
        phobi.bet(10000);

        // then
        assertThat(phobi.getBetAmount()).isEqualTo(10000);
    }

    @Test
    void 플레이어_승리_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        phobi.bet(10000);
        phobi.changeStatus(PlayerStatus.WIN);

        // when
        int finalMoney = phobi.getFinalMoney();

        // then
        assertThat(finalMoney).isEqualTo(10000);
    }

    @Test
    void 플레이어_패배_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        phobi.bet(10000);
        phobi.changeStatus(PlayerStatus.LOSS);

        // when
        int finalMoney = phobi.getFinalMoney();

        // then
        assertThat(finalMoney).isEqualTo(-10000);
    }

    @Test
    void 플레이어_블랙잭_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        phobi.bet(10000);
        phobi.changeStatus(PlayerStatus.BLACK_JACK);

        // when
        int finalMoney = phobi.getFinalMoney();

        // then
        assertThat(finalMoney).isEqualTo(15000);
    }

    @Test
    void 플레이어_무승부_최종수익() {
        // given
        Player phobi = Player.of("phobi");
        phobi.bet(10000);
        phobi.changeStatus(PlayerStatus.DRAW);

        // when
        int finalMoney = phobi.getFinalMoney();

        // then
        assertThat(finalMoney).isEqualTo(0);
    }
}
