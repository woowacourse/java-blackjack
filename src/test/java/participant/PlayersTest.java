package participant;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.CardShape;
import game.GameResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("모든 플레이어의 금액을 업데이트한다.")
    void test1() {
        // given
        Dealer dealer = new Dealer();
        Player miso = new Player("미소", 10000);
        Players players = new Players(List.of(miso));

        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.NINE));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));

        // when
        players.updateMoney(dealer);

        // then
        assertThat(miso.getEarnedMoney())
                .isEqualTo(20000);
    }

    @Test
    @DisplayName("모든 플레이어의 수익을 합해서 반환한다.")
    void test2() {
        // given
        int misoBettingMoney = 10000;
        int player2BettingMoney = 10000;
        Player miso = new Player("미소", misoBettingMoney);
        Player yulmu = new Player("율무", player2BettingMoney);
        Players players = new Players(List.of(miso, yulmu));
        GameResult misoGameResult = GameResult.WIN;
        GameResult yulmuGameResult = GameResult.WIN;
        miso.updateMoney(misoGameResult.getRate());
        yulmu.updateMoney(yulmuGameResult.getRate());

        // when
        Profit profit = players.sumProfits();

        // then
        assertThat(profit.getAmount())
                .isEqualTo((int) (misoBettingMoney * misoGameResult.getRate()
                        + player2BettingMoney * yulmuGameResult.getRate()));
    }
}
