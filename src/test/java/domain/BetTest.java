package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Player;
import domain.service.BlackJackService;
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
}
