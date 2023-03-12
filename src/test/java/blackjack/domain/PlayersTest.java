package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {
    @DisplayName("입력되는 이름의 수만큼 참가자를 생성한다.")
    @Test
    void Should_Create_When_NewPlayers() {
        assertThat(Players.from(List.of("name1", "name2", "name3")).getCount()).isEqualTo(3);
    }

    @DisplayName("플레이어 수는 1에서 6사이여야 한다.")
    @ParameterizedTest(name = "{displayName} [{index}] => ''{0}''")
    @ValueSource(ints = {0, 7})
    void Should_ThrowException_When_Between1And6(int size) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            names.add("newname");
        }

        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.");
    }
}
