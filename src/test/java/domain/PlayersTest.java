package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 플레이어의_이름이_중복될_경우_예외가_발생한다() {
        String roro = "뽀로로";
        String prin = "프린";
        List<String> playerNames = List.of(roro, prin, prin);

        assertThatThrownBy(() -> new Players(playerNames))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어들을_생성한다() {
        String roro = "뽀로로";
        String prin = "프린";
        String pobi = "포비";
        List<String> playerNames = List.of(roro, prin, pobi);

        Players players = new Players(playerNames);

        assertThat(players).extracting("players", InstanceOfAssertFactories.list(Player.class))
                .hasSize(3);
    }
}
