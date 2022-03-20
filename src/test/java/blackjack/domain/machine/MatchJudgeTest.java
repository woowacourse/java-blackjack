package blackjack.domain.machine;

import static blackjack.domain.card.Fixtures.HEART_ACE;
import static blackjack.domain.card.Fixtures.SPADE_ACE;
import static blackjack.domain.card.Fixtures.SPADE_EIGHT;
import static blackjack.domain.card.Fixtures.SPADE_FOUR;
import static blackjack.domain.card.Fixtures.SPADE_JACK;
import static blackjack.domain.card.Fixtures.SPADE_NINE;
import static blackjack.domain.card.Fixtures.SPADE_TEN;
import static blackjack.domain.card.Fixtures.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchJudgeTest {

    @Test
    @DisplayName("플레이어가 블랙잭, 딜러가 블랙잭, 버스트 아닐 시 블랙잭 승")
    void playerBlackjackWinWithDealerNonBust() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_JACK));
        Score dealerScore = new Score(Set.of(SPADE_ACE, SPADE_EIGHT));
        MatchJudge blackjackWin = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(blackjackWin).isEqualTo(MatchJudge.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭, 딜러가 버스트일 시 블랙잭 승")
    void playerBlackjackWinWithDealerBust() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_JACK));
        Score dealerScore = new Score(Set.of(SPADE_ACE, SPADE_EIGHT));
        MatchJudge blackjackWin = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(blackjackWin).isEqualTo(MatchJudge.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트, 딜러가 버스트일 시 패")
    void playerBustWithDealerBust() {
        Score playerScore = new Score(Set.of(SPADE_NINE, SPADE_EIGHT, SPADE_FOUR, SPADE_ACE));
        Score dealerScore = new Score(Set.of(SPADE_TEN, SPADE_JACK, SPADE_TWO));
        MatchJudge lose = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(lose).isEqualTo(MatchJudge.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트, 딜러가 버스트가 아닐 시 패")
    void playerScoreLowerThanDealerScore() {
        Score playerScore  = new Score(Set.of(SPADE_TEN, SPADE_JACK, SPADE_TWO));
        Score dealerScore = new Score(Set.of(SPADE_NINE, SPADE_EIGHT));
        MatchJudge lose = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(lose).isEqualTo(MatchJudge.LOSE);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 낮을 시 패")
    void playerBustDealerNonBust() {
        Score playerScore = new Score(Set.of(SPADE_NINE, SPADE_EIGHT));
        Score dealerScore = new Score(Set.of(SPADE_TEN, SPADE_JACK));
        MatchJudge lose = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(lose).isEqualTo(MatchJudge.LOSE);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭 시 무승부")
    void playerBlackjackDealerBlackjack() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_JACK));
        Score dealerScore = new Score(Set.of(HEART_ACE, SPADE_TEN));
        MatchJudge draw = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(draw).isEqualTo(MatchJudge.DRAW);
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭, 버스트가 아닌 같은 점수일 시 무승부")
    void playerNonBustDealerNonBustSameScore() {
        Score playerScore = new Score(Set.of(SPADE_NINE, SPADE_FOUR));
        Score dealerScore = new Score(Set.of(HEART_ACE, SPADE_TWO));
        MatchJudge draw = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(draw).isEqualTo(MatchJudge.DRAW);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 높을 때 승")
    void playerNonBustScoreMoreThanDealerNonBustScore() {
        Score playerScore = new Score(Set.of(SPADE_NINE, SPADE_FOUR, HEART_ACE));
        Score dealerScore = new Score(Set.of(HEART_ACE, SPADE_TWO));
        MatchJudge win = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(win).isEqualTo(MatchJudge.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트일 때 승")
    void playerNonBustScoreDealerBustScore() {
        Score playerScore = new Score(Set.of(SPADE_NINE, SPADE_FOUR, HEART_ACE));
        Score dealerScore = new Score(Set.of(SPADE_TEN, SPADE_JACK, SPADE_TWO));
        MatchJudge win = MatchJudge.judgeMatch(playerScore, dealerScore);

        assertThat(win).isEqualTo(MatchJudge.WIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACKJACK_WIN:10000:15000", "WIN:10000:10000", "DRAW:10000:0","LOSE:10000:-10000"}, delimiter = ':')
    void calculateProfit(MatchJudge matchJudge, double money, double profit) {
        double playerProfit = matchJudge.calculateProfit(money);

        assertThat(playerProfit).isEqualTo(profit);
    }
}
