package blackjack.domain.gamer;

import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("플레이어")
public class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayer() {
        // given & when
        Player player = new Player(new Name("lemone"), new Chip(0));

        // then
        assertThat(player.name()).isEqualTo("lemone");
    }
}
