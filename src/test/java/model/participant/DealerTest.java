package model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import model.betting.Bet;
import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("카드의 합이 16이하이면 true를 반환한다.")
    @Test
    void 카드합_16이하이면_true반환() {
        //given
        dealer.receiveCard(new Card(CardRank.EIGHT, CardSuit.HEART));
        dealer.receiveCard(new Card(CardRank.EIGHT, CardSuit.DIAMOND));

        //when, then
        assertTrue(dealer.canHit());
    }

    @DisplayName("카드의 합이 17이상의면 false를 반환한다.")
    @Test
    void 카드합_17이상이면_false를반환() {
        //given
        dealer.receiveCard(new Card(CardRank.EIGHT, CardSuit.HEART));
        dealer.receiveCard(new Card(CardRank.NINE, CardSuit.DIAMOND));

        //when, then
        assertFalse(dealer.canHit());
    }

    @DisplayName("첫 카드 배분 후 맨 처음에 뽑은 한 장만 공개한다.")
    @Test
    void 첫카드_배분후_한장_공개() {
        //given
        Card firstCard = new Card(CardRank.FIVE, CardSuit.DIAMOND);
        dealer.receiveCard(firstCard);
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        //when, then
        assertThat(dealer.openInitialDeal()).containsExactly(firstCard);
    }

    @DisplayName("플레이어가 제시한 배팅 금액을 딜러가 갖는다.")
    @Test
    void 플레이어_제시_배팅금액을_저장() {
        //given
        int money = 10000;
        Player better = new Player("moda");
        Bet bet = new Bet(money, better);

        //when
        dealer.receiveBet(bet);

        //then
        assertThat(dealer.calculateRevenue()).isEqualTo(money);
    }

    @DisplayName("플레이어가 승리하면 플레이어의 배팅 금액을 돌려준다")
    @Test
    void 플레이어_승리시_배팅금액을_돌려준다() {
        //given
        int money = 10000;
        Player better = new Player("moda");
        Bet bet = new Bet(money, better);
        dealer.receiveBet(bet);

        //then
        Bet returnedBet = dealer.returnBetOf(better);

        //then
        assertThat(returnedBet.equals(bet)).isTrue();
    }
}
