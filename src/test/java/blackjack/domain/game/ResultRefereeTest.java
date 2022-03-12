package blackjack.domain.game;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBust;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfFifteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ResultRefereeTest {

    private static final Dealer dealer20 = Dealer.of(getCardBundleOfTwenty());
    private static final Dealer dealerBlackjack = Dealer.of(getCardBundleOfBlackjack());
    private static final Dealer dealerBust = Dealer.of(getCardBundleOfBust());

    private static final Player player10 = Player.of("ten", getCardBundleOfTen());
    private static final Player player15 = Player.of("fifteen", getCardBundleOfFifteen());
    private static final Player player20 = Player.of("twenty", getCardBundleOfTwenty());
    private static final Player playerBlackjack = Player.of("blackjack", getCardBundleOfBlackjack());
    private static final Player playerBust = Player.of("bust", getCardBundleOfBust());

    private static final List<Integer> LOSE_ONCE = List.of(0, 1, 0);
    private static final List<Integer> WIN_ONCE = List.of(1, 0, 0);
    private static final List<Integer> DRAW_ONCE = List.of(0, 0, 1);

    @DisplayName("딜러가 블랙잭인 경우, 패가 21인 플레이어는 무승부, 그 외에는 전부 패배한다.")
    @Test
    void blackjackDealer() {
        List<ResultStatistics> results = new ResultReferee(
                dealerBlackjack, List.of(playerBlackjack,player20, playerBust))
                .getResults();

        ResultStatistics dealerResult = results.get(0);
        assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(2, 0, 1));
        assertThat(getResultCounts(results.get(1))).isEqualTo(DRAW_ONCE);
        assertThat(getResultCounts(results.get(2))).isEqualTo(LOSE_ONCE);
        assertThat(getResultCounts(results.get(3))).isEqualTo(LOSE_ONCE);
    }

    @DisplayName("플레이어가 버스트인 경우 무조건 딜러가 승리한다.")
    @Nested
    class PlayerBustTest {

        @DisplayName("플레이어와 딜러 모두 버스트인 경우, 나중에 버스트가 된 딜러가 승리한다.")
        @Test
        void playerLoseOnDealerBust() {
            List<ResultStatistics> results = new ResultReferee(dealerBust, List.of(playerBust))
                    .getResults();

            ResultStatistics dealerResult = results.get(0);
            assertThat(getResultCounts(dealerResult)).isEqualTo(WIN_ONCE);
            assertThat(getResultCounts(results.get(1))).isEqualTo(LOSE_ONCE);
        }

        @DisplayName("플레이어가 버스트인 경우, 점수가 더 낮은 딜러가 승리한다.")
        @Test
        void playerLoseOnDealerNotBust() {
            List<ResultStatistics> results = new ResultReferee(dealer20, List.of(playerBust))
                    .getResults();

            ResultStatistics dealerResult = results.get(0);
            assertThat(getResultCounts(dealerResult)).isEqualTo(WIN_ONCE);
            assertThat(getResultCounts(results.get(1))).isEqualTo(LOSE_ONCE);
        }
    }

    private List<Integer> getResultCounts(ResultStatistics dealerResult) {
        return Arrays.stream(ResultType.values())
                .map(type -> dealerResult.getCountOf(type).toInt())
                .collect(Collectors.toList());
    }

    @DisplayName("딜러가 버스트인 경우, 버스트가 아닌 플레이어는 전부 승리한다.")
    @Test
    void bustDealer() {
        List<ResultStatistics> results = new ResultReferee(dealerBust,
                List.of(player10, player15, player20, playerBust))
                .getResults();

        ResultStatistics dealerResult = results.get(0);
        assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(1, 3, 0));
        for (int i : List.of(1, 2, 3)) {
            assertThat(getResultCounts(results.get(i))).isEqualTo(WIN_ONCE);
        }
        assertThat(getResultCounts(results.get(4))).isEqualTo(LOSE_ONCE);
    }

    @DisplayName("딜러가 20 이하인 경우, 버스트인 플레이어는 패배, 그 외에는 전부 대소비교를 통해 판정한다.")
    @Test
    void simpleComparison() {
        List<ResultStatistics> results = new ResultReferee(dealer20,
                List.of(playerBlackjack, player20, player10, player15, playerBust))
                .getResults();

        ResultStatistics dealerResult = results.get(0);
        assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(3, 1, 1));
        assertThat(getResultCounts(results.get(1))).isEqualTo(WIN_ONCE);
        assertThat(getResultCounts(results.get(2))).isEqualTo(DRAW_ONCE);
        for (int i : List.of(3, 4, 5)) {
            assertThat(getResultCounts(results.get(i))).isEqualTo(LOSE_ONCE);
        }
    }
}
