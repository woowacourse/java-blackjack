package blackjack.domain.game;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static blackjack.domain.fixture.CardRepository.CLOVER9;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerMatchResultTest {
    @DisplayName("of 에 Player 리스트와 Dealer 를 전달하면, Dealer 의 승패무 결과를 갖고있는 DealerMatchResult 인스턴스를 반환한다.")
    @Test
    void of_returns() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER9, CLOVER7));
        Player winnerPlayer = Player.of("winner", Hand.of(CLOVER10, CLOVER7));
        Player loserPlayer = Player.of("loser", Hand.of(CLOVER8, CLOVER7));

        // when
        DealerMatchResult dealerMatchResult = DealerMatchResult.of(dealer, List.of(winnerPlayer, loserPlayer));
        Map<ResultType, ResultCount> matchResult = dealerMatchResult.getMatchResult();

        // then
        assertThat(matchResult.get(ResultType.WIN)).isEqualTo(new ResultCount(1));
        assertThat(matchResult.get(ResultType.LOSE)).isEqualTo(new ResultCount(1));
        assertThat(matchResult.get(ResultType.DRAW)).isEqualTo(new ResultCount(0));
    }
}
