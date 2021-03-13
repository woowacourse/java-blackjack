package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    @DisplayName("이름 입력 분리")
    void playerNameSplit() {
        Dealer dealer = new Dealer();
        Players players = new Players("pobi,jason", dealer);

        Players comparePlayers = new Players("pobi,jason", dealer);

        assertThat(players).isEqualTo(comparePlayers);
    }
}
