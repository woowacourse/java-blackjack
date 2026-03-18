package domain;

import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    @Test
    @DisplayName("플레이어 인원 수는 5명 이하여야 합니다.")
    void 플레이어인원수_5명이하_성공() {
        // given
        Map<String, Integer> players = new HashMap<>();
        players.put("pobi", 1000);
        players.put("james", 2000);

        // when - then
        assertDoesNotThrow(() -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 인원 수는 6명 이상인 경우 오류가 발생해야 한다.")
    void 플레이어인원수_6명이상_오류() {
        // given
        Map<String, Integer> playerBets = new HashMap<>();
        playerBets.put("pobi", 1000);
        playerBets.put("james", 1000);
        playerBets.put("eunoh", 1000);
        playerBets.put("ruro", 1000);
        playerBets.put("ruro1", 1000);
        playerBets.put("ruro2", 1000);

        // when - then
        assertThatThrownBy(() -> new Players(playerBets))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 인원 수는 5명 이하여야 합니다.");
    }
}
