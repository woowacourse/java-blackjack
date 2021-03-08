package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    @DisplayName("이름 입력 분리")
    void playerNameSplit() {
        String input = "pobi,jason";
        Players players = new Players(input, new Dealer());
        Players comparePlayers = new Players("pobi,jason", new Dealer());

        assertThat(players).isEqualTo(comparePlayers);
    }
}
