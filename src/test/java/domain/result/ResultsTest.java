package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Dealer;
import domain.Player;
import domain.TestData;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultsTest {

    @Test
    @DisplayName("플레이어가 각각 -100, 200의 수익을 가져야 한다.")
    void getResultsTest1() {
        Dealer dealer = TestData.getScore20Dealer();
        Player player1 = TestData.getScore19Player(100);
        Player player2 = TestData.getScore21Player(200);
        Results results = Results.of(dealer, List.of(player1, player2));

        Result dealerResult = results.getDealerResult();
        assertThat(dealerResult.getProfit()).isEqualTo(-100);

        List<Result> playerResults = results.getPlayerResults();
        assertThat(playerResults.get(0).getProfit()).isEqualTo(-100);
        assertThat(playerResults.get(1).getProfit()).isEqualTo(200);
    }
    @Test
    @DisplayName("플레이어가 각각 -100, 300의 수익을 가져야 한다.")
    void getResultsTest2() {
        Dealer dealer = TestData.getScore20Dealer();
        Player player1 = TestData.getScore19Player(100);
        Player player2 = TestData.getBlackJackPlayer(200);
        Results results = Results.of(dealer, List.of(player1, player2));

        Result dealerResult = results.getDealerResult();
        assertThat(dealerResult.getProfit()).isEqualTo(-200);

        List<Result> playerResults = results.getPlayerResults();
        assertThat(playerResults.get(0).getProfit()).isEqualTo(-100);
        assertThat(playerResults.get(1).getProfit()).isEqualTo(300);
    }
}
