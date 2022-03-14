package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("처음 플레이어 가져오는 기능 테스트")
    @Test
    void firstPlayerTest() {
        Players players = new Players(List.of("pobi", "jason"));
        assertThat(players.firstPlayer().getName().equals("pobi")).isTrue();
    }

    @DisplayName("다음 플레이어 가져오는 기능 테스트")
    @Test
    void nextPlayerTest() {
        Players players = new Players(List.of("pobi", "jason"));
        assertThat(players.nextPlayer(players.firstPlayer()).getName().equals("jason")).isTrue();
    }

    @DisplayName("다음 플레이어 없을때 가져오면 null리턴 하는지 테스트")
    @Test
    void nextPlayerTest2() {
        Players players = new Players(List.of("pobi", "jason"));
        Player player = players.nextPlayer(players.firstPlayer());
        assertThat(players.nextPlayer(player)).isNull();
    }

    @DisplayName("다음 플레이어 있을때 true 리턴하는지 확인하는 테스트")
    @Test
    void hasNextPlayerTest() {
        Players players = new Players(List.of("pobi", "jason"));
        assertThat(players.hasNextPlayer(players.firstPlayer())).isTrue();
    }

    @DisplayName("다음 플레이어 없을때 false 리턴하는지 확인하는 테스트")
    @Test
    void hasNextPlayerTest2() {
        Players players = new Players(List.of("pobi", "jason"));
        Player player = players.nextPlayer(players.firstPlayer());
        assertThat(players.hasNextPlayer(player)).isFalse();
    }
}
