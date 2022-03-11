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

    public static final Dealer dealer20 = Dealer.of(getCardBundleOfTwenty());
    public static final Dealer dealerBlackjack = Dealer.of(getCardBundleOfBlackjack());
    public static final Dealer dealerBust = Dealer.of(getCardBundleOfBust());

    private final Player player10 = Player.of("ten", getCardBundleOfTen());
    private final Player player15 = Player.of("fifteen", getCardBundleOfFifteen());
    private final Player player20 = Player.of("twenty", getCardBundleOfTwenty());
    private final Player playerBlackjack = Player.of("blackjack", getCardBundleOfBlackjack());
    private final Player playerBust = Player.of("bust", getCardBundleOfBust());

    @DisplayName("딜러가 블랙잭인 경우, 패가 21인 플레이어는 무승부, 그 외에는 전부 패배한다.")
    @Test
    void blackjackDealer() {
        List<ResultStatistics> results = new ResultReferee(
                dealerBlackjack, List.of(player20, playerBlackjack, playerBust))
                .getResults();

        ResultStatistics dealerResult = results.get(0);
        assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(2, 0, 1));
        assertThat(getResultCounts(results.get(1))).isEqualTo(List.of(0, 1, 0));
        assertThat(getResultCounts(results.get(2))).isEqualTo(List.of(0, 0, 1));
        assertThat(getResultCounts(results.get(3))).isEqualTo(List.of(0, 1, 0));
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
            assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(1, 0, 0));
            assertThat(getResultCounts(results.get(1))).isEqualTo(List.of(0, 1, 0));
        }

        @DisplayName("플레이어가 버스트인 경우, 점수가 더 낮은 딜러가 승리한다.")
        @Test
        void playerLoseOnDealerNotBust() {
            List<ResultStatistics> results = new ResultReferee(dealer20, List.of(playerBust))
                    .getResults();

            ResultStatistics dealerResult = results.get(0);
            assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(1, 0, 0));
            assertThat(getResultCounts(results.get(1))).isEqualTo(List.of(0, 1, 0));
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
        List<ResultStatistics> results = new ResultReferee(
                dealerBust, List.of(player10, player15, player20, playerBust))
                .getResults();

        ResultStatistics dealerResult = results.get(0);
        assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(1, 3, 0));
        assertThat(getResultCounts(results.get(1))).isEqualTo(List.of(1, 0, 0));
        assertThat(getResultCounts(results.get(2))).isEqualTo(List.of(1, 0, 0));
        assertThat(getResultCounts(results.get(3))).isEqualTo(List.of(1, 0, 0));
        assertThat(getResultCounts(results.get(4))).isEqualTo(List.of(0, 1, 0));
    }

    @DisplayName("딜러가 20 이하인 경우, 버스트인 플레이어는 패배, 그 외에는 전부 대소비교를 통해 판정한다.")
    @Test
    void simpleComparison() {
        List<ResultStatistics> results = new ResultReferee(
                dealer20, List.of(player10, player15, player20, playerBlackjack, playerBust))
                .getResults();

        ResultStatistics dealerResult = results.get(0);
        assertThat(getResultCounts(dealerResult)).isEqualTo(List.of(3, 1, 1));
        assertThat(getResultCounts(results.get(1))).isEqualTo(List.of(0, 1, 0));
        assertThat(getResultCounts(results.get(2))).isEqualTo(List.of(0, 1, 0));
        assertThat(getResultCounts(results.get(3))).isEqualTo(List.of(0, 0, 1));
        assertThat(getResultCounts(results.get(4))).isEqualTo(List.of(1, 0, 0));
        assertThat(getResultCounts(results.get(5))).isEqualTo(List.of(0, 1, 0));
    }
}
