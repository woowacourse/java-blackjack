package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("플레이어 리스트를 통해 Players를 생성할 수 있다.")
    void test1() {
        List<Player> players = List.of(new Player("모루"),
                new Player("띠용"),
                new Player("차니"),
                new Player("히포"),
                new Player("히로"));

        assertThat(new Players(players)).isInstanceOf(Players.class);
    }

    @Test
    @DisplayName("입장하는 플레이어 수가 최대 인원수를 초과하면 예외를 던진다.")
    void test2() {
        List<Player> players = List.of(new Player("모루"),
                new Player("띠용"),
                new Player("차니"),
                new Player("히포"),
                new Player("히로"),
                new Player("서프"));

        assertThatThrownBy(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
    }
}
