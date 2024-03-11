package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.ParticipantName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetResultTest {

    @DisplayName("플레이어의 수익 합산의 반대로 딜러의 수익을 계산한다.")
    @Test
    void calculateDealerRevenue() {
        // given
        Map<ParticipantName, BetRevenue> playersBetRevenu = Map.of(new ParticipantName("pobi"), new BetRevenue(1000),
                new ParticipantName("kirby"), new BetRevenue(2000),
                new ParticipantName("json"), new BetRevenue(-1000));

        BetResult betResult = new BetResult(playersBetRevenu);

        // when
        BetRevenue dealerRevenue = betResult.calculateDealerRevenue();

        // then
        assertThat(dealerRevenue).isEqualTo(new BetRevenue(-2000));
    }
}
