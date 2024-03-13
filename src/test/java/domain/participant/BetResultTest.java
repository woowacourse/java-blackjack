package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.*;

class BetResultTest {

    @DisplayName("게임 시작시 배팅 금액을 정한다.")
    @Test
    void betting() {
        LinkedHashMap<Participant, BetAmount> result = new LinkedHashMap<>();
        Participant participant = new Participant(new Name("rush"));
        BetAmount betAmount = BetAmount.from(1000);

        result.put(participant, betAmount);
        BetResult betResult = new BetResult(result);

        BetAmount findBetAmount = betResult.findByParticipant(participant);
        assertThat(findBetAmount).isEqualTo(betAmount);
    }
}
