package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private static final int TEST_PLAYER_BET_AMOUNT = 10000;

    @Test
    @DisplayName("중복된 이름이 있으면 예외가 발생한다.")
    void duplicatedNamesTest() {
        List<String> names = List.of("loki", "pedro", "loki");
        List<PlayerMeta> playerMetas = names.stream()
                        .map(name -> new PlayerMeta(name, TEST_PLAYER_BET_AMOUNT))
                                .toList();

        assertThatThrownBy(() -> Players.from(playerMetas))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 존재합니다.");
    }
}
