package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어를 생성한다.")
    @Test
    void create() {
        Player player = new Player("papi");
        assertThat(player).isEqualTo(new Player("papi"));
    }

}
