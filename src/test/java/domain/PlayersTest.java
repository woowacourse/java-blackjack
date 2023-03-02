package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("참여자 수는 최대 9명이다.")
    void createPlayersFail() {
        List<String> playerNames = List.of("a", "b", "c", "d", "f", "q", "w", "e", "r", "z");

        assertThatThrownBy(() -> Player.of(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자는 최대 9명까지 가능합니다.");
    }

}
