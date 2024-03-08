import domain.balckjack.BlackJackResult;
import domain.participant.Name;
import domain.participant.Participant;
import domain.balckjack.WinStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

class BlackJackResultTest {

    @DisplayName("딜러가 이긴 횟수 반환")
    @Test
    void getDealerWinCount() {
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(new Participant(new Name("rush")), WinStatus.WIN);
        result.put(new Participant(new Name("pobi")), WinStatus.WIN);
        result.put(new Participant(new Name("bito")), WinStatus.LOSE);
        BlackJackResult blackJackResult = new BlackJackResult(result);

        Assertions.assertThat(blackJackResult.getDealerWinCount()).isEqualTo(1);
    }

    @DisplayName("승패의 총 합계 반환")
    @Test
    void getTotalCount() {
        LinkedHashMap<Participant, WinStatus> result = new LinkedHashMap<>();
        result.put(new Participant(new Name("rush")), WinStatus.WIN);
        result.put(new Participant(new Name("pobi")), WinStatus.WIN);
        result.put(new Participant(new Name("bito")), WinStatus.LOSE);
        BlackJackResult blackJackResult = new BlackJackResult(result);

        Assertions.assertThat(blackJackResult.getTotalCount()).isEqualTo(3);
    }
}
