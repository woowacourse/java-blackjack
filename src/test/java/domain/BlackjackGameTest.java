package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BlackjackGameTest {
    @DisplayName("올바르지 않은 덱의 개수로 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 3, 10})
    void 올바르지_않은_덱의_개수(int inputDeckCount) {
        Map<String, Integer> playerBettingAmountTable = new LinkedHashMap<>() {{
            put("Player1", 1_000);
            put("Player2", 2_000);
        }};
        assertThatThrownBy(() -> BlackjackGame.of(playerBettingAmountTable, inputDeckCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[1, 2, 4, 6, 8]개의 덱만 사용 가능합니다.");
    }
}