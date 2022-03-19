package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.ParticipantAcceptStrategy;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProfitCalculatorTest {

    @Test
    @DisplayName("딜러의 수익금은 참가자의 수익금의 -1배이다.")
    void calculateDealerProfit() {
        assertThat(ProfitCalculator.calculateDealerProfit(List.of(-1000, 20000))).isEqualTo(-19000);
    }
}
