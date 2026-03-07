package domain.participant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayersTest {

    @Test
    @DisplayName("플레이어 인원 수는 5명 이하여야 합니다.")
    void 플레이어인원수_5명이하_성공() {
        // given
        List<String> players = List.of("pobi", "james");

        // when - then
        Assertions.assertDoesNotThrow(() -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 인원 수는 6명 이상인 경우 오류가 발생해야 한다.")
    void 플레이어인원수_6명이상_오류() {
        // given
        List<String> players = List.of("pobi", "james", "eunoh", "ruro", "ruro1", "ruro2");

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 이름이 중복되는 경우 오류가 발생해야 한다.")
    void 플레이어_이름_중복_금지() {
        // given
        List<String> players = List.of("pobi", "james", "eunoh", "ruro", "ruro");

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }
}
