package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("플레이어의 배팅금을 넣는 테스트")
    void betting() {
        Player player = new Player(new Name("test"), 100);
        assertThat(player.getBetting()).isEqualTo(100);
    }
}
