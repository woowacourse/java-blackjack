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
        Players players = new Players(Arrays.asList("pika", "air"));
        List<Player> participants = players.getPlayers();

        assertThat(participants).isNotNull();
    }

    @Test
    @DisplayName("참가자 생성 실패")
    void createPlayersFail() {
        assertThatThrownBy(() -> new Players(Arrays.asList("", ""))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players(Arrays.asList("pika", "", "air"))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players(Arrays.asList(" ", " ", " "))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자 8명 초과 시 에러 발생")
    void checkPlayersCount() {
        assertThatThrownBy(() -> new Players(Arrays.asList("pika", "air", "a", "b", "c", "d", "e", "f", "g"))).isInstanceOf(IllegalArgumentException.class);
    }
}
