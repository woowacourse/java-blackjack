package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.Player;
import blackjack.domain.state.State;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BettingsTest {

    @Test
    void 베팅_정보_저장_기능_테스트() {
        // given
        Player player = new Player("밀란");
        int bettingAmount = 1_000;

        // when & then
        assertThatCode(() -> Bettings.of(Map.of(player, new BettingAmount(bettingAmount))))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어와_상태를_알면_수익을_알_수_있다() {
        // given
        Player player = new Player("밀란");
        int bettingAmount = 1_000;
        Bettings bettings = Bettings.of(Map.of(player, new BettingAmount(bettingAmount)));

        // when
        int profit = (int) bettings.calculateProfit(player, State.BLACKJACK);

        // then
        assertThat(profit).isEqualTo(1_500);
    }

    @Test
    void 등록되지_않은_플레이어를_받으면_오류를_일으킨다() {
        // given
        Player player = new Player("밀란");
        Player other = new Player("없음");
        int bettingAmount = 1_000;
        Bettings bettings = Bettings.of(Map.of(player, new BettingAmount(bettingAmount)));

        // when & then
        assertThatThrownBy(() -> bettings.calculateProfit(other, State.BLACKJACK))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
