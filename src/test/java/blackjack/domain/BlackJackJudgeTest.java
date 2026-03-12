package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackJudgeTest {


    @Test
    void 처음_두장으로_21이면_블랙잭_승리다() {
        Players players = new Players(List.of("pobi"));
        Player player = players.getPlayers().get(0);

        player.setBetAmount(1000);
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.KING, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.DIAMOND));

        BlackJackJudge judge = new BlackJackJudge();

        FinalIncome result = judge.judge(players, dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1500);
    }


    @Test
    void 세장의_합이_21이면_일반_승리이지_블랙잭_승리는_아니다() {
        Players players = new Players(List.of("pobi"));
        Player player = players.getPlayers().get(0);

        player.setBetAmount(1000);
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.CLUB));

        BlackJackJudge judge = new BlackJackJudge();

        FinalIncome result = judge.judge(players, dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1000);
    }


}
