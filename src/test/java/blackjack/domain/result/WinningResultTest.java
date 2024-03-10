package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @DisplayName("딜러의 승패를 통계낸다.")
    @Test
    void summarizeDealerResult() {
        Players players = Players.from(List.of("kirby", "pobi"));
        players.divideCard(List.of(List.of(new Card(CardNumber.ACE, CardShape.CLOVER),
                        new Card(CardNumber.TEN, CardShape.CLOVER)),
                List.of(new Card(CardNumber.NINE, CardShape.SPADE), new Card(CardNumber.TEN, CardShape.SPADE))));

        WinningResult winningResult = WinningResult.of(players, new Score(20));

        Map<WinStatus, Long> dealerResult = winningResult.summarizeDealerWinningResult();

        assertThat(dealerResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                WinStatus.WIN, 1L,
                WinStatus.LOSE, 1L));
    }
}
