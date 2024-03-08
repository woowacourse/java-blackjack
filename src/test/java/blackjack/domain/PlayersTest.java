package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("게임의 플레이어는 최소 1명이상이 참여해야 합니다")
    @Test
    void should_ThrowIllegalArgumentException_When_Lower_Than_Minimum_PlayerNumber() {
        List<String> playerNames = List.of();
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게임의 플레이어는 최대 8명까지 참여할 수 있습니다")
    @Test
    void should_ThrowIllegalArgumentException_When_More_Than_Maximum_PlayerNumber() {
        List<String> playerNames = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
