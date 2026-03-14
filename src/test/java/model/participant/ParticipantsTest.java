package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {
    private Participants participants;

    @BeforeEach
    void setUp() {
        participants = new Participants();
        participants.addPlayer(new Player(new PlayerName("player1")));
    }


    @Test
    public void 정상_작동() {
        participants.addPlayer(new Player(new PlayerName("player2")));

        List<String> playerNames = participants.getPlayerNames();

        assertThat(playerNames.size()).isEqualTo(2);
        assertThat(playerNames.getFirst()).isEqualTo("player1");
        assertThat(playerNames.getLast()).isEqualTo("player2");
    }

    @Test
    public void 딜러_이름_참가자_예외() {
        Player player = new Player(new PlayerName("딜러"));
        assertThatThrownBy(() -> participants.addPlayer(player)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
    }

    @Test
    public void 플레이어_가져오기_없는_이름_예외() {
        assertThatThrownBy(() -> participants.getPlayerCurrentHand("player2"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NO_PLAYER_NAME.getMessage());
    }
}
