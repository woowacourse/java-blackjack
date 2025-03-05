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
        Player player1 = new Player("pobi", null);
        Player player2 = new Player("woni", null);
        Player player3 = new Player("brie", null);
        Player player4 = new Player("neo", null);
        Player player5 = new Player("norang", null);
        Player player6 = new Player("haru", null);
        List<Player> players = List.of(player1, player2, player3, player4, player5, player6);
        // when && then
        assertThatThrownBy(() -> new Game(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 참여자는 최대 5명입니다.");
    }
}
