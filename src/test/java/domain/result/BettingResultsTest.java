package domain.result;

import domain.participant.Money;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingResultsTest {

    @Test
    @DisplayName("플레이어의 수익 상태가 올바르게 변경된다.")
    void shouldSuccessChangeBetResult() {
        Player dino = new Player(new Name("dino"));
        BettingResults bettingResults = new BettingResults();
        bettingResults.initParticipantBet(dino, new Money("10000"));

        SoftAssertions.assertSoftly(softly -> {
            bettingResults.plusBettingResult(dino, -10000);
            softly.assertThat(bettingResults.getParticipantBet(dino).getAmount()).isEqualTo(0);

            bettingResults.plusBettingResult(dino, -10000);
            softly.assertThat(bettingResults.getParticipantBet(dino).getAmount()).isEqualTo(-10000);

            bettingResults.plusBettingResult(dino, 25000);
            softly.assertThat(bettingResults.getParticipantBet(dino).getAmount()).isEqualTo(15000);
        });
    }
}
