package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.gameRule.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DealerTest {

    @DisplayName("딜러가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Dealer dealer = new Dealer(100);
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        dealer.receiveCard(card);
        List<Card> dealerDeck = dealer.allCards();

        //then
        assertThat(dealerDeck).containsExactly(card);
    }

    @DisplayName("딜러의 카드 개수를 확인한다.")
    @Test
    void deckSize() {
        //given
        Dealer dealer = new Dealer(100);
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        int deckSize = dealer.deckSize();

        //then
        assertThat(deckSize).isEqualTo(2);
    }

    @DisplayName("딜러의 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Dealer dealer = new Dealer(100);
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = new Card(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        int totalScore = dealer.totalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("딜러의 스코어가 17 미만이라면 히트할 수 있다.")
    @Test
    void canHitByExpectedTrue() {
        //given
        Dealer dealer = new Dealer(100);
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        dealer.receiveCard(card);

        //then
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 스코어가 17 이상이라면 히트할 수 없다.")
    @Test
    void canHitByExpectedFalse() {
        //given
        Dealer dealer = new Dealer(100);
        Card card1 = new Card(CardPattern.CLOVER, CardNumber.TEN);
        Card card2 = new Card(CardPattern.SPADE, CardNumber.TEN);
        Card card3 = new Card(CardPattern.CLOVER, CardNumber.ACE);

        //when
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        dealer.receiveCard(card3);

        //then
        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("플레이어에 게임 수익을 딜러의 지급금에 등록한다.")
    @ParameterizedTest
    @EnumSource(Result.class)
    void payPlayerProfit(Result gameResult) {
        //given
        int betAmount = 1000;
        Player player = new Player(new Name("ted"), betAmount);
        Dealer dealer = new Dealer(betAmount);

        player.applyResult(gameResult);
        int expectedProfitAmount = (int) (betAmount * gameResult.getPayoutRate());

        //when
        dealer.payPlayerProfit(player);
        int payoutAmount = dealer.payoutAmount();

        //then
        assertThat(payoutAmount).isEqualTo(expectedProfitAmount);
    }

    @DisplayName("플레이어에게 지급할 수익을 제외한 순수익을 확인한다.")
    @ParameterizedTest
    @EnumSource(Result.class)
    void netProfit(Result gameResult) {
        //given
        int betAmount = 1000;
        Player player = new Player(new Name("ted"), betAmount);
        Dealer dealer = new Dealer(betAmount);

        player.applyResult(gameResult);
        int profitAmount = (int) (betAmount * gameResult.getPayoutRate());
        int expectedNetProfit = betAmount - profitAmount;

        //when
        dealer.payPlayerProfit(player);
        int netProfit = dealer.netProfit();

        //then
        assertThat(netProfit).isEqualTo(expectedNetProfit);
    }
}
