package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CurrentPlayersTest {
    private Players players;

    @BeforeEach
    public void setUp() {
        players = new Players(null);
    }

    @Test
    public void 플레이어_등록_정상_작동() {
        PlayerName player = new PlayerName("player1");
        PlayerName player2 = new PlayerName("player2");

        players.addPlayer(player);
        players.addPlayer(player2);

        List<String> playerNames = players.getPlayerNames();

        assertThat(playerNames.size()).isEqualTo(2);
        assertThat(playerNames.getFirst()).isEqualTo("player1");
        assertThat(playerNames.getLast()).isEqualTo("player2");
    }

    @Test
    public void 중복_이름_참가자_예외() {
        PlayerName player1 = new PlayerName("player");
        PlayerName player2 = new PlayerName("player");

        players.addPlayer(player1);

        assertThatThrownBy(() -> players.addPlayer(player2))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_NAME.getMessage());
    }
}
