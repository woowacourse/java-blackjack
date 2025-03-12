package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어 리스트를 통해 Players를 생성할 수 있다.")
    void test1() {
        List<String> players = List.of("모루", "띠용", "차니", "히포", "히로");

        assertThat(new Players(players)).isInstanceOf(Players.class);
    }

    @Test
    @DisplayName("입장하는 플레이어 수가 최대 인원수를 초과하면 예외를 던진다.")
    void test2() {
        List<String> players = List.of("모루", "띠용", "차니", "히포", "히로", "서프");

        assertThatThrownBy(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
    }
}
