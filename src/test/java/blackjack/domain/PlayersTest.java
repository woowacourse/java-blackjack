package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayersTest {

    @DisplayName("문자열을 받아 Player를 생성한다.")
    @Test
    void createPlayersWhenGivenString() {
        // given
        String input = "pobi,jason";

        // when
        Players players = new Players(input);

        // then
        assertThat(players.getPlayers().size()).isEqualTo(2);
    }
}
