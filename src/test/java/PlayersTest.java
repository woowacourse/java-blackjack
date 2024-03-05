import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @DisplayName("플레이어들 간 중복된 이름을 가질 경우 예외를 발생시킨다.")
    @Test
    void createDomainByDuplicatedNames() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("player"));
        players.add(new Player("player"));
        players.add(new Player("player2"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
    }

    @DisplayName("플레이어가 1명보다 적을 경우 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidMinSize() {
        List<Player> players = new ArrayList<>();
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 최소 1명에서 최대 8명까지 참여할 수 있습니다.");
    }

    @DisplayName("플레이어가 8명이 넘어갈 경우 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidMaxSize() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("player1"));
        players.add(new Player("player2"));
        players.add(new Player("player3"));
        players.add(new Player("player4"));
        players.add(new Player("player5"));
        players.add(new Player("player6"));
        players.add(new Player("player7"));
        players.add(new Player("player8"));
        players.add(new Player("player9"));
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 최소 1명에서 최대 8명까지 참여할 수 있습니다.");
    }

    @DisplayName("올바른 조건의 플레이어가 전달된 경우 성공적으로 도메인을 생성한다.")
    @Test
    void createDomainSuccessfully() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("player1"));
        players.add(new Player("player2"));
        assertThatCode(() -> new Players(players))
                .doesNotThrowAnyException();
    }
}
