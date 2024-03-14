package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("성공: 경계값 1명의 플레이어 생성")
    void from_NoException_OnePlayer() {
        Players players = Players.withNames(List.of("가aA12"));
        assertThat(players.getPlayers()).hasSize(1);
    }

    @Test
    @DisplayName("성공: 경계값 7명의 플레이어 생성")
    void from_NoException_SevenPlayers() {
        Players players = Players.withNames(List.of(
            "플레이어1", "user2", "USER3", "4", "5", "6", "7"
        ));
        assertThat(players.getPlayers()).hasSize(7);
    }

    @Test
    @DisplayName("실패: 경계값 0명의 플레이어 생성")
    void from_Exception_ZeroPlayers() {
        assertThatThrownBy(() -> Players.withNames(List.of()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1~7명의 플레이어만 허용합니다.");
    }

    @Test
    @DisplayName("실패: 경계값 8명의 플레이어 생성")
    void from_Exception_EightPlayers() {
        assertThatThrownBy(() -> Players.withNames(List.of("1", "2", "3", "4", "5", "6", "7", "8")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1~7명의 플레이어만 허용합니다.");
    }

    @Test
    @DisplayName("실패: 중복되는 플레이어 생성")
    void from_Exception_DuplicatedPlayers() {
        assertThatThrownBy(() -> Players.withNames(List.of("a", "a")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("실패: null 플레이어 생성")
    void from_Exception_NullPlayers() {
        assertThatThrownBy(() -> Players.withNames(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 플레이어 이름은 null일 수 없습니다.");
    }
}
