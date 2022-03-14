package blackjack.domain.game;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static blackjack.domain.fixture.CardRepository.CLOVER9;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardBundle;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RefereeTest {

    @DisplayName("generateDealerResult 에 Player 리스트와 Dealer 를 전달하면, Dealer 의 승패무 결과를 반환한다.")
    @Test
    void generateDealerResult() {
        // given
        Referee referee = new Referee();
        Dealer dealer = Dealer.of(CardBundle.of(CLOVER9, CLOVER7));
        Player winnerPlayer = Player.of("winner", CardBundle.of(CLOVER10, CLOVER7));
        Player loserPlayer = Player.of("loser", CardBundle.of(CLOVER8, CLOVER7));

        // when
        Map<ResultType, ResultCount> result = referee.generateDealerResult(dealer, Set.of(winnerPlayer, loserPlayer));

        // then
        assertThat(result.get(ResultType.WIN)).isEqualTo(new ResultCount(1));
        assertThat(result.get(ResultType.LOSE)).isEqualTo(new ResultCount(1));
        assertThat(result.get(ResultType.DRAW)).isEqualTo(new ResultCount(0));
    }

    @DisplayName("generatePlayerResult 에 점수가 높은 플레이어와 낮은 플레이어를 전달하면, ResultType.WIN 을 반환한다.")
    @Test
    void generatePlayerResult_returnsWin() {
        // given
        Referee referee = new Referee();
        Player winnerPlayer = Player.of("winner", CardBundle.of(CLOVER10, CLOVER7));
        Player loserPlayer = Player.of("loser", CardBundle.of(CLOVER8, CLOVER7));

        // when
        ResultType actual = referee.generatePlayerResult(winnerPlayer, loserPlayer);
        ResultType expected = ResultType.WIN;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("generatePlayerResult 에 점수가 낮은 플레이어와 높은 플레이어를 전달하면, ResultType.LOSE 을 반환한다.")
    @Test
    void generatePlayerResult_returnsLose() {
        // given
        Referee referee = new Referee();
        Player winnerPlayer = Player.of("winner", CardBundle.of(CLOVER10, CLOVER7));
        Player loserPlayer = Player.of("loser", CardBundle.of(CLOVER8, CLOVER7));

        // when
        ResultType actual = referee.generatePlayerResult(loserPlayer, winnerPlayer);
        ResultType expected = ResultType.LOSE;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("generatePlayerResult 에 점수가 같은 두 플레이어를 전달하면, ResultType.DRAW 을 반환한다.")
    @Test
    void generatePlayerResult_returnsDraw() {
        // given
        Referee referee = new Referee();
        Player player1 = Player.of("winner", CardBundle.of(CLOVER10, CLOVER7));
        Player player2 = Player.of("loser", CardBundle.of(CLOVER9, CLOVER8));

        // when
        ResultType actual = referee.generatePlayerResult(player1, player2);
        ResultType expected = ResultType.DRAW;

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
