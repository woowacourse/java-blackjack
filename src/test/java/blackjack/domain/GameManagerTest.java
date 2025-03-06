package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameManagerTest {

    @Test
    @DisplayName("참가자를 추가한다")
    void 참가자를_추가한다() {
        List<String> names = List.of("비타", "두리");

        GameManager gameManager = new GameManager();
        gameManager.addGamblers(names);

        assertThat(gameManager.getPlayers().getGamblers().size())
                .isEqualTo(2);
    }
}
