package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @Test
    @DisplayName("참여자가 5명이 넘어갈 경우 예외가 발생한다.")
    void testValidatePlayerCount() {
        // given
        List<String> playerNames = List.of("pobi", "woni", "brie", "neo", "norang", "haru");
        // when && then
        assertThatThrownBy(() -> new Game(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 참여자는 최대 5명입니다.");
    }

    @Test
    @DisplayName("이름이 중복될 경우 예외가 발생한다.")
    void testValidateDuplicateName() {
        // given
        List<String> playerNames = List.of("pobi", "pobi");
        // when && then
        assertThatThrownBy(() -> new Game(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 이름은 중복될 수 없습니다.");
    }
}
