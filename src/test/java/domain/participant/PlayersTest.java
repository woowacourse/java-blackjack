package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PlayersTest {
    @Test
    @DisplayName("중복된 이름입력에 대해 입력했을 때 예외를 반환한다.")
    void testSuccessParseCase() {
        String inputNames = "pobi, woni";
        Players players = Players.fromString(inputNames);

        List<String> playerNames = players.getPlayerNames();

        assertThat(playerNames).isEqualTo(List.of("pobi", "woni"));
    }


    @Test
    @DisplayName("중복된 이름입력에 대해 입력했을 때 예외를 반환한다.")
    void testDuplicateNameParseCase() {
        String inputNames = "pobi, pobi";
        assertThrows(IllegalArgumentException.class, () -> Players.fromString(inputNames));
    }

    @Test
    @DisplayName("플레이어의 수는 1명 이상 8명 이하여야 한다.")
    void testPlayerCountsParseCase() {
        String inputNames = "유저1, 유저2, 유저3, 유저4, 유저5, 유저6, 유저7, 유저8, 유저9";
        assertThrows(IllegalArgumentException.class,  () -> Players.fromString(inputNames));
    }
}
