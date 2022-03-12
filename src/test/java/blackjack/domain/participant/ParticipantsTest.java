package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("플레이어가 아무도 없을 때 예외처리")
    void validateEmptyName() {
        Assertions.assertThatThrownBy(
                () -> new Participants(new ArrayList<>())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("턴을 스킵했을 때 다음 참가자로 넘어가는지 테스트")
    void playersSkipTest() {
        Player pobi = new Player("pobi", new ArrayList<>());
        Player elsa = new Player("elsa", new ArrayList<>());
        List<Participant> players = Arrays.asList(pobi, elsa);

        Participants participants = new Participants(players);
        participants.skipTurn();
        Participant nextPlayer = participants.getCurrentPlayer();

        Assertions.assertThat(nextPlayer).isEqualTo(elsa);
    }

    @Test
    @DisplayName("모든 플레이어가 끝났는지 확인하는 기능")
    void allPlayersFinished() {
        Player pobi = new Player("pobi", new ArrayList<>());
        Player elsa = new Player("elsa", new ArrayList<>());
        List<Participant> players = Arrays.asList(pobi, elsa);

        Participants participants = new Participants(players);
        participants.skipTurn();
        participants.skipTurn();

        Assertions.assertThat(participants.isFinished()).isTrue();
    }
}
