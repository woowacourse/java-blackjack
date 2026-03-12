package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

class BettingsTest {

    @Test
    void 베팅_정보_저장_및_찾기_기능_테스트() {
        // given
        Bettings bettings = new Bettings();
        Player player = new Player("밀란");
        BettingAmount bettingAmount = new BettingAmount(1000);

        // when
        bettings.put(player, bettingAmount);

        // then
        assertThat(bettings.findByPlayer(player)).isEqualTo(bettingAmount);
    }

}
