package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("참가자 생성 성공")
    void createPlayersSucceed() {
        Players players = new Players(Arrays.asList(new Player("pika"), new Player("air")));
        List<Player> participants = players.getPlayers();

        assertThat(participants).isNotNull();
    }

    @Test
    @DisplayName("참가자 생성 실패")
    void createPlayersFail() {
        assertThatThrownBy(() -> new Players(Arrays.asList(new Player(""), new Player("")))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players(Arrays.asList(new Player("pika"), new Player("")))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players(Arrays.asList(new Player(" "), new Player("air")))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자 8명 초과 시 에러 발생")
    void checkPlayersCount() {
        assertThatThrownBy(() -> new Players(
                Arrays.asList(
                        new Player("pika"),
                        new Player("air"),
                        new Player("a"),
                        new Player("b"),
                        new Player("c"),
                        new Player("d"),
                        new Player("e"),
                        new Player("f"),
                        new Player("g")
                ))).isInstanceOf(IllegalArgumentException.class);
    }
}
