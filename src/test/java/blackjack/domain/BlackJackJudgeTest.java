package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackJudgeTest {

    private Player createPlayer(String name, int betAmount) {
        return new Player(new Name(name), new BetAmount(betAmount));
    }

    private Players createPlayers(Player... players) {
        return new Players(List.of(players));
    }

    @Test
    void 처음_두장으로_21이면_블랙잭_승리다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.KING, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.DIAMOND));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1500);
    }

    @Test
    void 세장의_합이_21이면_일반_승리이지_블랙잭_승리는_아니다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.CLUB));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1000);
    }

    @Test
    void 플레이어가_블랙잭이고_딜러가_일반21이면_블랙잭_승리다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.KING, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.SEVEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.SEVEN, CardPattern.DIAMOND));
        dealer.recieveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1500);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이면_무승부다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.KING, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.ACE, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(0);
    }

    @Test
    void 플레이어가_버스트이면_패배다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.QUEEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.KING, CardPattern.HEART));
        player.recieveCard(new Card(CardPoint.TWO, CardPattern.CLUB));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.CLUB));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(-1000);
    }

    @Test
    void 딜러가_버스트이면_플레이어_승리다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.NINE, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.QUEEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.KING, CardPattern.DIAMOND));
        dealer.recieveCard(new Card(CardPoint.TWO, CardPattern.HEART));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_딜러보다_높으면_승리다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.NINE, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.EIGHT, CardPattern.DIAMOND));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_딜러보다_낮으면_패배다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.SEVEN, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.DIAMOND));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(-1000);
    }

    @Test
    void 동점이면_무승부다() {
        Player player = createPlayer("pobi", 1000);
        player.recieveCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        player.recieveCard(new Card(CardPoint.NINE, CardPattern.HEART));

        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.TEN, CardPattern.CLUB));
        dealer.recieveCard(new Card(CardPoint.NINE, CardPattern.DIAMOND));

        FinalIncome result = new BlackJackJudge().judge(createPlayers(player), dealer);

        assertThat(result.getIncomeOf(player)).isEqualTo(0);
    }
}
