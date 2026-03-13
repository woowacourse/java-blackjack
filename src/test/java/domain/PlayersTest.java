package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("플레이어가 5명 이하면 정상적으로 Players 객체를 생성한다.")
    void shouldReturnPlayersWhenPlayerNumberIsMaximumOrLess() {
        // given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump", "junny");

        // when & then
        assertDoesNotThrow(
                () -> Players.of(testPlayerNames)
        );
    }

    @Test
    @DisplayName("플레이어가 5명을 초과하면 오류가 발생한다.")
    void shouldThrowExceptionWhenPlayerNumberOverMaximum() {
        // given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump", "junny", "aron");

        // when & then
        assertThatThrownBy(() -> Players.of(testPlayerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MAX_PLAYER_ERROR.getMessage());
    }
}
