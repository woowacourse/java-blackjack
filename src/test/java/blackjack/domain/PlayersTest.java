package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("이름 입력 분리")
    void playerNameSplit() {
        String input = "pobi,jason";
        Players players = new Players(input);
        assertThat(new Players("pobi,jason")).isEqualTo(players);
    }
}
