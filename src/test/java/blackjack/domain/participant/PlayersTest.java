package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("게임의 플레이어는 최소 1명이상이 참여해야 합니다")
    @Test
    void should_ThrowIllegalArgumentException_When_Lower_Than_Minimum_PlayerNumber() {
        List<String> emptyPlayers = new ArrayList<>();
        assertThatThrownBy(() -> new Players(emptyPlayers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참여자는 최소 1명에서 최대 8명까지 가능합니다");
    }

    @DisplayName("게임의 플레이어는 최대 8명까지 참여할 수 있습니다")
    @Test
    void should_ThrowIllegalArgumentException_When_More_Than_Maximum_PlayerNumber() {
        List<String> exceedPlayers = new ArrayList<>(List.of("pobi1", "pobi2", "pobi3", "pobi4", "pobi5",
                "pobi6", "pobi7", "pobi8", "pobi9"));

        assertThatThrownBy(() -> new Players(exceedPlayers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참여자는 최소 1명에서 최대 8명까지 가능합니다");
    }
}
