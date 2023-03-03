package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {

    @DisplayName("생성 테스트")
    @Test
    void Should_Create_When_NewPlayers() {
        List<Player> players = new ArrayList<>();
        assertDoesNotThrow(() -> new Players(players));
    }

    @DisplayName("플레이어 수는 1에서 6사이여야 한다.")
    @ParameterizedTest()
    @ValueSource(ints = {0, 7})
    void Should_ThrowException_When_Between1And6(int size) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            players.add(new Player(new Name("newName")));
        }
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.");
    }
}
