package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어의 이름을 보고 해당 플레이어를 찾는다.")
    @Test
    void findPlayerByName() {
        final Map<String, Double> bettings = new LinkedHashMap<>();
        bettings.put("pobi", (double) 10000);
        bettings.put("jason", (double) 20000);
        final Players players = Players.from(bettings);

        final Player foundPlayer = players.findBy("pobi");

        assertThat(foundPlayer.getName().value()).isEqualTo("pobi");
    }
}
