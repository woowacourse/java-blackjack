package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.participant.Player;
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

}
