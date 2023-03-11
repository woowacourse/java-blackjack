package blackjack.domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.PlayerName;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BettingTableTest {

    @Nested
    class getPlayerBetting_메서드는 {

        @Test
        void 플레이어_이름이_존재하지_않으면_예외를_던진다() {
            final Map<PlayerName, Betting> betting = new HashMap<>();
            final BettingTable bettingTable = new BettingTable(betting);

            assertThatThrownBy(() -> bettingTable.getPlayerBetting(new PlayerName("toney")))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 플레이어_이름이_존재하면_베팅을_반환한다() {
            final Map<PlayerName, Betting> betting = new HashMap<>();
            betting.put(new PlayerName("toney"), new Betting(1000));
            final BettingTable bettingTable = new BettingTable(betting);

            assertThat(bettingTable.getPlayerBetting(new PlayerName("toney")).getValue()).isEqualTo(1000);
        }
    }
}
