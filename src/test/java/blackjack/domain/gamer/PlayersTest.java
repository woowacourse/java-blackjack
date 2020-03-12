package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    @Test
    @DisplayName("플레이어 이름 분리 테스트")
    void split() {
        String names = "pobi,jun,woni";
        Players players = Players.ofComma(names);

        assertThat(players.getNames()).isEqualTo("pobi, jun, woni");
    }
}