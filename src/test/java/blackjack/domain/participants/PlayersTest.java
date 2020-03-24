package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayersTest {
    @DisplayName("플레이어의 수가 2명보다 적거나 8명보다 많을 때 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"1", "1, 2, 3, 4, 5, 6, 7, 8, 9"})
    void playerSizeTest(String input) {
        assertThatThrownBy(() -> {
            new Players(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
