package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import blackjack.domain.gameresult.WinningResult;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RevenueTest {

    @Test
    @DisplayName("플레이어가 블랙잭이 아닌 상태로 이기면 배팅금액만큼 수익이 발생한다.")
    void generateRevenuePlayerWinButNotBlackjack() {
        // given
        BettingMoney bettingMoney = new BettingMoney("1000");
        Player player = new Player(new ParticipantName("ako"), new Hand(), bettingMoney);
        WinningResult winningResult = WinningResult.WIN;

        //when
        Revenue result = Revenue.generateRevenue(player, winningResult);

        //then
        assertThat(result.getRevenue()).isEqualTo(bettingMoney.getMoney());
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이기면 상금은 배팅금액에 1.5배이다.")
    void generateRevenuePlayerWinBlackjack() {
        //given
        BettingMoney bettingMoney = new BettingMoney("1000");
        int expect = (int) (bettingMoney.getMoney() * 1.5);
        Player player = new Player(new ParticipantName("ako"), new Hand(), bettingMoney);
        playerHitCard(player);
        WinningResult winningResult = WinningResult.WIN;

        //when
        Revenue result = Revenue.generateRevenue(player, winningResult);

        //then
        assertThat(result.getRevenue()).isEqualTo(expect);
    }

    @Test
    @DisplayName("게임 결과과 무승부이면 수익금액은 0원이다.")
    void generateRevenuePlayerPush() {
        //given
        BettingMoney bettingMoney = new BettingMoney("1000");
        Player player = new Player(new ParticipantName("ako"), new Hand(), bettingMoney);
        WinningResult winningResult = WinningResult.PUSH;

        //when
        Revenue result = Revenue.generateRevenue(player, winningResult);

        //then
        assertThat(result.getRevenue()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 지면 Lose")
    void generateRevenuePlayerLose() {
        //given
        BettingMoney bettingMoney = new BettingMoney("1000");
        int expect = bettingMoney.getMoney() * -1;
        Player player = new Player(new ParticipantName("ako"), new Hand(), bettingMoney);
        WinningResult winningResult = WinningResult.LOSE;

        //when
        Revenue result = Revenue.generateRevenue(player, winningResult);

        //then
        assertThat(result.getRevenue()).isEqualTo(expect);
    }

    private void playerHitCard(final Player player) {
        List<Card> cards = List.of(
            new Card(CardNumber.ACE, CardSuit.DIAMOND),
            new Card(CardNumber.KING, CardSuit.CLUB));
        for (Card card : cards) {
            player.hit(card);
        }
    }
}