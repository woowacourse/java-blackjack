package domain.participant;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DealerTest {

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.receive(new Card(Rank.ACE, Suit.DIAMOND));

        // then
        Assertions.assertEquals(dealer.getCards().size(), 1);
    }

    @Test
    @DisplayName("16이하 시, 무조건 hit")
    void hit_16이하() {
        // given
        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.ACE, Suit.DIAMOND));
        dealer.receive(new Card(Rank.FIVE, Suit.CLOVER));

        // when - then
        Assertions.assertTrue(dealer.shouldHit());
    }

    @Test
    @DisplayName("17 이상 시, 무조건 stand")
    void stand_17이상() {
        // given
        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.SEVEN, Suit.DIAMOND));
        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));

        // when - then
        Assertions.assertFalse(dealer.shouldHit());
    }

    @Test
    @DisplayName("딜러는 첫 번째 카드만 보여준다.")
    void 첫장_오픈() {
        // given
        Dealer dealer = new Dealer();
        Card firstCard = new Card(Rank.SEVEN, Suit.DIAMOND);
        dealer.receive(firstCard);
        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));

        // when - then
        Assertions.assertEquals(dealer.getFirstCard(), firstCard);
    }

    @Test
    @DisplayName("딜러의 승패를 올바르게 판단한다.")
    void 딜러_승패_판단() {
        // given
        Dealer dealer = new Dealer();
        Map<Player, MatchResult> playerResults = new HashMap<>();

        playerResults.put(new Player("pobi"), MatchResult.WIN);
        playerResults.put(new Player("sisi"), MatchResult.WIN);
        playerResults.put(new Player("ao"), MatchResult.WIN);
        playerResults.put(new Player("james"), MatchResult.DRAW);
        playerResults.put(new Player("lala"), MatchResult.LOSE);

        // when
        Map<MatchResult, Integer> dealerResult = dealer.calculateResult(playerResults);

        // then
        Assertions.assertEquals(dealerResult.get(MatchResult.LOSE), 3);
        Assertions.assertEquals(dealerResult.get(MatchResult.DRAW), 1);
        Assertions.assertEquals(dealerResult.get(MatchResult.WIN), 1);
    }

    @Test
    @DisplayName("플레이어의 수익 결과에 따른 딜러의 수익을 계산한다.")
    void calculateProfitResultTest() {
        // given
        Dealer dealer = new Dealer();
        Map<Player, Integer> playerProfitResults = new HashMap<>();

        playerProfitResults.put(new Player("pobi"), 20000);
        playerProfitResults.put(new Player("sisi"), 1000);
        playerProfitResults.put(new Player("ao"), -1000);
        playerProfitResults.put(new Player("james"), 15000);
        playerProfitResults.put(new Player("lala"), -10000);

        // when
        ;

        // then
        Assertions.assertEquals(dealer.calculateProfitResult(playerProfitResults), -25000);
    }
}
