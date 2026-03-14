package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private List<Player> createDummyPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName), new BetMoney(1000));
            players.add(player);
        }
        return players;
    }

    @Test
    @DisplayName("플레이어가 5명 이하면 정상적으로 Players 객체를 생성한다.")
    void shouldReturnPlayersWhenPlayerNumberIsMaximumOrLess() {
        // given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump", "junny");
        List<Player> testPlayers = createDummyPlayers(testPlayerNames);

        // when & then
        assertDoesNotThrow(
                () -> Players.of(testPlayers)
        );
    }

    @Test
    @DisplayName("플레이어가 5명을 초과하면 오류가 발생한다.")
    void shouldThrowExceptionWhenPlayerNumberOverMaximum() {
        // given
        List<String> testPlayerNames = List.of("pobi", "terry", "rati", "gump", "junny", "aron");
        List<Player> testPlayers = createDummyPlayers(testPlayerNames);

        // when & then
        assertThatThrownBy(() -> Players.of(testPlayers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
