package blackjacktest.domaintest.gamertest;

import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("참가자 생성 성공")
    void createPlayersSucceed() {
        Players players = new Players("pika, air");
        List<Player> participants = players.getPlayers();

        assertThat(participants).isEqualTo(Arrays.asList(new Player("pika"), new Player("air")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pika, ,air", ", , , ,"})
    @DisplayName("참가자 생성 실패")
    void createPlayersFail(String input) {
        assertThatThrownBy(() -> new Players(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
