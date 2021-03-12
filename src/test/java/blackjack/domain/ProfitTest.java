package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.view.OutputView;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {

    private Player test1;
    private Player test2;
    private Player test3;
    private Dealer dealer;
    private Players players;

    @BeforeEach
    void setUp() {
        test1 = new Player("test1", "10000");
        test2 = new Player("test2", "20000");
        test3 = new Player("test3", "30000");
        dealer = new Dealer();
        players = new Players(Arrays.asList(test1, test2, test3), dealer);
    }

    private void playersSetToStay(Players players) {

        for (Gamer gamer : players.getAllParticipant()) {
            gamer.state = gamer.state.stay();
        }
    }

    @Test
    @DisplayName("수익률이 0인 경우")
    void playersProfitTest() {
        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test2.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test3.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));

        playersSetToStay(players);
        players.calculateProfit();

        OutputView.noticePlayersPoint(players);
        OutputView.noticeMatchProfit(players);
        assertThat(dealer.earnedMoney).isEqualTo(0);
    }

    @Test
    @DisplayName("수익률이 -인 경우")
    void negativeProfitTest() {
        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.ACE));
        test2.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test3.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TWO));

        playersSetToStay(players);
        players.calculateProfit();

        OutputView.noticePlayersPoint(players);
        OutputView.noticeMatchProfit(players);

        assertThat(dealer.earnedMoney).isEqualTo(-60000);
    }

    @Test
    @DisplayName("수익률이 +인 경우")
    void positiveProfitTest() {
        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test2.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test3.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.ACE));

        playersSetToStay(players);
        players.calculateProfit();

        OutputView.noticePlayersPoint(players);
        OutputView.noticeMatchProfit(players);

        assertThat(dealer.earnedMoney).isEqualTo(60000);
    }

    @Test
    @DisplayName("사용자가 블랙잭일 경우")
    void playerBlackjackTest() {

        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.ACE));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        Players players2 = new Players(Arrays.asList(test1), dealer);

        players2.calculateProfit();
        OutputView.noticePlayersPoint(players2);
        OutputView.noticeMatchProfit(players);
        assertThat(dealer.earnedMoney).isEqualTo(-15000);
    }

    @Test
    @DisplayName("블랙잭으로 패배하였을 때 0원만 잃어야 한다")
    void dealerBlackjackTest() {
        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test2.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test3.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));

        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.ACE));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));

        players.calculateProfit();
        OutputView.noticePlayersPoint(players);
        OutputView.noticeMatchProfit(players);
        assertThat(dealer.earnedMoney).isEqualTo(60000);
    }

    @Test
    @DisplayName("두 사람은 지고 한 사람은 이기는 경우")
    void profitSumTest() {
        test1.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test2.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test3.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        test3.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));

        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(Card.valueOf(CardPattern.CLOVER, CardNumber.FIVE));

        playersSetToStay(players);
        players.calculateProfit();
        OutputView.noticePlayersPoint(players);
        OutputView.noticeMatchProfit(players);
        assertThat(dealer.earnedMoney).isEqualTo(0);
    }
}
