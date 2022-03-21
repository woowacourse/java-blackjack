package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("플레이어가 1명 미만일 시 오류 발생")
    void createPlayersNumberException() {
        List<Player> playerList = List.of();

        assertThatThrownBy(() ->
            new Participants(playerList)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 8명 초과일 시 오류 발생")
    void createPlayersNumberException2() {
        List<Player> playerList = List.of(
            new Player("1", 0),
            new Player("2", 0),
            new Player("3", 0),
            new Player("4", 0),
            new Player("5", 0),
            new Player("6", 0),
            new Player("7", 0),
            new Player("8", 0),
            new Player("9", 0)
        );

        assertThatThrownBy(() ->
            new Participants(playerList)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 차례가 모두 끝났는지 체크")
    void isAllPlayerTurnEnd() {
        Player player = new Player("player", 0);
        Player player2 = new Player("player2", 0);
        Participants participants = new Participants(List.of(player, player2));

        participants.stayCurrentPlayer();
        participants.stayCurrentPlayer();

        assertThat(participants.isAllPlayerTurnEnd()).isTrue();
    }

    @Test
    @DisplayName("차례가 남은 플레이어가 없는 경우 현재 플레이어를 get할 수 없음")
    void getCurrentPlayerError() {
        Player player = new Player("player", 0);
        Player player2 = new Player("player2", 0);
        Participants participants = new Participants(List.of(player, player2));

        while (!participants.isAllPlayerTurnEnd()) {
            participants.stayCurrentPlayer();
        }

        assertThat(participants.isAllPlayerTurnEnd()).isTrue();
    }
}
