package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 플레이어의_수가_범위를_벗어날_경우_예외가_발생한다() {
        List<String> playerNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이", "수달");

        assertThatThrownBy(() -> new Players(playerNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 수는 1 ~ 6명이어야 합니다.");
    }

    @Test
    void 플레이어의_이름이_중복될_경우_예외가_발생한다() {
        String roro = "뽀로로";
        String prin = "프린";
        List<String> playerNames = List.of(roro, prin, prin);

        assertThatThrownBy(() -> new Players(playerNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
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
