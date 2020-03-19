package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("비어있는 플레이어로 Players 생성시 예외 발생")
    void playersEmptyException() {
        assertThatThrownBy(() -> new Players(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null로 플레이어 생성시 예외 발생")
    void playersNullException() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어는 List<String>으로 생성")
    void from() {
        List<String> names = Arrays.asList("pobi", "jun", "woni");
        Players players = Players.from(names);

        assertThat(players).isInstanceOf(Players.class);
    }

    @Test
    @DisplayName("players 생성시 비어 있는 이름 리스트는 예외 발생")
    void playerNamesEmptyException() {
        assertThatThrownBy(() -> Players.from(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("players 생성시 null이 입력되면 예외 발생")
    void playerNamessNullException() {
        assertThatThrownBy(() -> Players.from(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Players에서 player이름으로 player를 찾음")
    void findPlayerByName() {
        Player pobi = new Player("pobi");
        Player jay = new Player("jay");
        Player elly = new Player("elly");
        Players players = new Players(Arrays.asList(pobi, jay, elly));

        assertThat(players.findPlayerBy("jay")).isEqualTo(jay);
    }

    @Test
    @DisplayName("players에서 getNames를 하면 플레이어들의 이름을 반환")
    void getPlayerNames() {
        Player pobi = new Player("pobi");
        Player jay = new Player("jay");
        Player elly = new Player("elly");
        Players players = new Players(Arrays.asList(pobi, jay, elly));

        assertThat(players.getNames())
                .contains("pobi")
                .contains("jay")
                .contains("elly")
                .size().isEqualTo(3);
    }
}