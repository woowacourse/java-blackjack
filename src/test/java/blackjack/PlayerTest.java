package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어를 생성한다.")
    @Test
    void create() {
        Player player = new Player("파피");
        assertThat(player.getName()).isEqualTo("파피");
    }

}
