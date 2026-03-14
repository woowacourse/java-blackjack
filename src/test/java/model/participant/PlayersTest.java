package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    private Players players;

    @BeforeEach
    public void setUp() {
        players = new Players();
    }

    @Test
    public void 플레이어_등록_정상_작동() {
        Player player = new Player(new PlayerName("player1"));
        Player player2 = new Player(new PlayerName("player2"));

        players.addPlayer(player);
        players.addPlayer(player2);

        List<String> playerNames = players.getPlayerNames();

        assertThat(playerNames.size()).isEqualTo(2);
        assertThat(playerNames.getFirst()).isEqualTo("player1");
        assertThat(playerNames.getLast()).isEqualTo("player2");
    }

    @Test
    public void 중복_이름_참가자_예외() {
        PlayerName playerName = new PlayerName("player");
        Player player1 = new Player(playerName);
        Player player2 = new Player(new PlayerName("player"));
        Player player3 = new Player(playerName);

        players.addPlayer(player1);

        assertThatThrownBy(() -> players.addPlayer(player2)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(
                ErrorMessage.DUPLICATED_NAME.getMessage());
        assertThatThrownBy(() -> players.addPlayer(player3)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(
                ErrorMessage.DUPLICATED_NAME.getMessage());
    }
}
