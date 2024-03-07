import domain.BlackJackResult;
import domain.Name;
import domain.Participant;
import domain.WinStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BlackJackResultTest {

    @DisplayName("딜러가 이긴 횟수 반환")
    @Test
    void getDealerWinCount() {
        Map<Participant, WinStatus> result = new HashMap<>();
        result.put(new Participant(new Name("rush")), WinStatus.WIN);
        result.put(new Participant(new Name("pobi")), WinStatus.WIN);
        result.put(new Participant(new Name("bito")), WinStatus.LOSE);
        BlackJackResult blackJackResult = new BlackJackResult(result);

        Assertions.assertThat(blackJackResult.getDealerWinCount()).isEqualTo(1);
    }

    @DisplayName("승패의 총 합계 반환")
    @Test
    void getTotalCount() {
        Map<Participant, WinStatus> result = new HashMap<>();
        result.put(new Participant(new Name("rush")), WinStatus.WIN);
        result.put(new Participant(new Name("pobi")), WinStatus.WIN);
        result.put(new Participant(new Name("bito")), WinStatus.LOSE);
        BlackJackResult blackJackResult = new BlackJackResult(result);

        Assertions.assertThat(blackJackResult.getTotalCount()).isEqualTo(3);
    }
}
