package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름들을 받아서 플레이어 그룹을 생성한다.")
    void createPlayers() {
        List<String> names = List.of("리브", "몰리");
        assertThatCode(() -> Players.from(names));
    }
}
