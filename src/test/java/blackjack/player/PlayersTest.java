package blackjack.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름이 중복될 경우 예외를 발생시킨다")
    void duplicateNameTest() {
        // given
        List<String> names = List.of("aru", "atto", "aru");
        // when, then
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 수가 제한을 초과하는 경우 예외를 발생시킨다.")
    void exceedingPlayersSizeTest() {
        // given
        List<String> playerNames = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        // when, then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 1명 이상 10명 이하여야 합니다.");
    }

    @Test
    @DisplayName("플레이어가 없는 경우 예외를 발생시킨다.")
    void emptyPlayersSizeTest() {
        // given
        List<String> playerNames = List.of();
        // when, then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 1명 이상 10명 이하여야 합니다.");
    }

    @Test
    @DisplayName("플레이어로 null이 전달되는 경우 예외를 발생시킨다.")
    void nullPlayerTest() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어로 null이 전달되었습니다.");
    }
}
