package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player(new Name("pobi"));
    }

    @Test
    @DisplayName("플레이어 이름 반환 테스트")
    void testPlayerName() {
        assertThat(this.player.getName()).isEqualTo("pobi");
    }
}
