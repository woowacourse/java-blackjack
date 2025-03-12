package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import blackjack.domain.GameResult;
import blackjack.domain.TestUtil;
import blackjack.domain.card.Card;

import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = TestUtil.createPlayerFromName("player");

        // when
        boolean canHit = player.canHit();

        // then
        assertThat(canHit).isTrue();
    }

    @DisplayName("카드가 21이 초과한다면 카드를 더 뽑을 수 없다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        boolean canHit = player.canHit();

        // then
        assertThat(canHit).isFalse();
    }

    @DisplayName("플레이어는 처음에 받은 2장의 카드를 모두 보여준다.")
    @Test
    void test_showStartCards() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        List<Card> startCards = player.showStartCards();

        // then
        assertThat(startCards).hasSize(2);
    }

    @DisplayName("플레이어가 이겼을때 배팅금액의 1배가 수익이다.")
    @Test
    void test_profitWhenWin() {
        // given
        ParticipantName playerName = new ParticipantName("player");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player player = new Player(playerName, new CardHand(), bettingMoney);

        // when
        int profit = player.calculateProfit(GameResult.WIN);

        // then
        assertThat(profit).isEqualTo(10000);
    }

    @DisplayName("플레이어가 블랙잭으로 이겼을때 배팅금액의 1.5배가 수익이다.")
    @Test
    void test_profitWhenBlackjackWin() {
        // given
        ParticipantName playerName = new ParticipantName("player");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player player = new Player(playerName, new CardHand(), bettingMoney);

        // when
        int profit = player.calculateProfit(GameResult.BLACKJACK_WIN);

        // then
        assertThat(profit).isEqualTo(15000);
    }

    @DisplayName("플레이어가 졌을 때 배팅금 모두를 잃는다.")
    @Test
    void test_profitWhenLose() {
        // given
        ParticipantName playerName = new ParticipantName("player");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player player = new Player(playerName, new CardHand(), bettingMoney);

        // when
        int profit = player.calculateProfit(GameResult.LOSE);

        // then
        assertThat(profit).isEqualTo(-10000);
    }

    @DisplayName("무승부면 수익이 없다(0원이다)")
    @Test
    void test_profitWhenDraw() {
        // given
        ParticipantName playerName = new ParticipantName("player");
        BettingMoney bettingMoney = new BettingMoney(10000);
        Player player = new Player(playerName, new CardHand(), bettingMoney);

        // when
        int profit = player.calculateProfit(GameResult.DRAW);

        // then
        assertThat(profit).isEqualTo(0);
    }

}