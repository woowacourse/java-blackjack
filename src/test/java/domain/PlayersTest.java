package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("모든 플레이어의 이름을 반환한다")
    @Test
    void test1() {
        // given
        Player player1 = new Player("웨이드1");
        Player player2 = new Player("웨이드2");
        Player player3 = new Player("웨이드3");
        Players players = new Players(List.of(player1, player2, player3));
        // when
        List<String> allPlayersName = players.getAllPlayersName();
        // then
        assertSoftly(softly -> {
            softly.assertThat(allPlayersName).hasSize(3);
            softly.assertThat(allPlayersName.get(0)).isEqualTo("웨이드1");
            softly.assertThat(allPlayersName.get(1)).isEqualTo("웨이드2");
            softly.assertThat(allPlayersName.get(2)).isEqualTo("웨이드3");
        });
    }

    @DisplayName("플레이어의 이름으로 플레이어를 찾을 수 있다")
    @Test
    void test2() {
        // given
        Player player1 = new Player("웨이드1");
        Player player2 = new Player("웨이드2");
        Player player3 = new Player("웨이드3");
        Players players = new Players(List.of(player1, player2, player3));
        // when
        Player player = players.findPlayerByName("웨이드1");
        // then
        Assertions.assertThat(player).isEqualTo(player1);
    }

    @DisplayName("딜러와 참여자의 카드로 모든 플레이어의 수익을 계산한다")
    @Test
    void test3() {
        //given
        Dealer dealer = Dealer.createEmpty();
        dealer.getHand().add(new Card(CardNumberType.EIGHT, CardType.DIAMOND));
        dealer.getHand().add(new Card(CardNumberType.QUEEN, CardType.DIAMOND));

        Hand winHand = new Hand(List.of(
                new Card(CardNumberType.ACE, CardType.DIAMOND),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND)
        ));
        BettingMoney winBettingMoney = new BettingMoney(10000);
        Hand drawHand = new Hand(List.of(
                new Card(CardNumberType.EIGHT, CardType.DIAMOND),
                new Card(CardNumberType.QUEEN, CardType.SPACE)
        ));
        BettingMoney drawBettingMoney = new BettingMoney(20000);
        Hand loseHand = new Hand(List.of(
                new Card(CardNumberType.FIVE, CardType.DIAMOND),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND)
        ));
        BettingMoney loseBettingMoney = new BettingMoney(30000);

        Player winner = new Player("mimi", winHand, winBettingMoney);
        Player drawer = new Player("wade", drawHand, drawBettingMoney);
        Player loser = new Player("pobi", loseHand, loseBettingMoney);
        Players players = new Players(List.of(winner, drawer, loser));

        //when
        ProfitResults profitResults = players.calculateProfitResults(dealer.getHand());

        //then
        assertSoftly(softly -> {
            softly.assertThat(profitResults.getProfitValue(winner)).isEqualTo(10000);
            softly.assertThat(profitResults.getProfitValue(drawer)).isEqualTo(0);
            softly.assertThat(profitResults.getProfitValue(loser)).isEqualTo(-30000);
        });
    }
}
