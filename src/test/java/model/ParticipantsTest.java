package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    public void 정상_작동() {
        Player player = new Player(new PlayerName("player1"));
        Player player2 = new Player(new PlayerName("player2"));

        Participants participants = new Participants(List.of(player,player2));

        List<Player> playerList = participants.getPlayers();

        assertThat(playerList.size()).isEqualTo(2);
        assertThat(playerList.getFirst()).isEqualTo(player);
        assertThat(playerList.getLast()).isEqualTo(player2);
    }

    @Test
    public void 중복_이름_예외() {
        PlayerName playerName = new PlayerName("player");

        Player player = new Player(playerName);
        Player player2 = new Player(playerName);

        assertThatThrownBy(() -> new Participants(List.of(player,player2)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_NAME.getMessage());
    }
}
