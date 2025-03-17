package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BettingMoney;
import blackjack.domain.BlackjackGame;
import blackjack.domain.GameResult;
import blackjack.domain.TestUtil;
import blackjack.domain.card.Card;

import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.state.Blackjack;
import blackjack.state.Stand;
import blackjack.state.Start;
import blackjack.state.State;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        Player player = TestUtil.createStartPlayerOf("player", new CardHand());
        Card card1 = new Card(CardSuit.CLUB, CardRank.NINE);
        Card card2 = new Card(CardSuit.CLUB, CardRank.SEVEN);
        player.startGame(card1, card2);

        // when
        boolean canHit = player.canHit();

        // then
        assertThat(canHit).isTrue();
    }

    @DisplayName("카드가 21 이상이면 카드를 더 뽑을 수 없다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.ACE));
        Player player = TestUtil.createStandPlayerOf(cardHand);

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

        Player player = TestUtil.createStartPlayerOf("player", cardHand);

        // when
        List<Card> startCards = player.showStartCards();

        // then
        assertThat(startCards).hasSize(2);
    }

    @DisplayName("플레이어가 이겼을때 배팅금액의 1배가 수익이다.")
    @Test
    void test_profitWhenWin() {
        // given
        int bet = 10000;
        CardHand playerHand = new CardHand();
        playerHand.add(new Card(CardSuit.SPADE, CardRank.KING));
        Player player = TestUtil.createStandPlayerOf(playerHand, bet);

        Stand dealerState = new Stand(new CardHand());

        // when
        int profit = player.calculateProfit(dealerState);

        // then
        assertThat(profit).isEqualTo(10000);
    }

    @DisplayName("플레이어가 블랙잭으로 이겼을때 배팅금액의 1.5배가 수익이다.")
    @Test
    void test_profitWhenBlackjackWin() {
        // given
        int bet = 10000;
        Player player = TestUtil.createBlackjackPlayerWithBet(bet);

        Stand dealerState = new Stand(new CardHand());

        // when
        int profit = player.calculateProfit(dealerState);

        // then
        assertThat(player.getState()).isInstanceOf(Blackjack.class);
        assertThat(profit).isEqualTo(15000);
    }

    @DisplayName("플레이어가 졌을 때 배팅금 모두를 잃는다.")
    @Test
    void test_profitWhenLose() {
        // given
        int bet = 10000;
        Player player = TestUtil.createStandPlayerOf(new CardHand(), bet);

        Blackjack dealerState = new Blackjack(new CardHand());

        // when
        int profit = player.calculateProfit(dealerState);

        // then
        assertThat(profit).isEqualTo(-10000);
    }

    @DisplayName("무승부면 수익이 없다(0원이다)")
    @Test
    void test_profitWhenDraw() {
        // given
        int bet = 10000;
        Player player = TestUtil.createStandPlayerOf(new CardHand(), bet);

        Stand dealerState = new Stand(new CardHand());

        // when
        int profit = player.calculateProfit(dealerState);

        // then
        assertThat(profit).isEqualTo(0);
    }

}