package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("본인의 이름을 반환한다.")
    void returnName() {
        // given
        String playerName = "Player Name";

        // when
        Player player = new Player(playerName);

        // then
        assertThat(player.getName()).isEqualTo(playerName);
    }
}
