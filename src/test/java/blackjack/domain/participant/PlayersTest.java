package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("이름 입력 분리")
    void playerNameSplit() {
        String input = "pobi,jason";
        Players players = new Players(input, new Dealer());
        assertThat(new Players("pobi,jason", new Dealer())).isEqualTo(players);
    }
}
