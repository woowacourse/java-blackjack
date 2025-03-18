package model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import model.betting.Bet;
import model.betting.Money;
import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @DisplayName("딜러가 에이스 카드를 최초로 뽑으면 SoftHand를 갖는다.")
    @Test
    void 소프트밸류_카드한장을_최초로_뽑을때_소프트핸드를_갖는다() {
        //given
        Card card = new Card(CardRank.ACE, CardSuit.HEART);

        //when
        dealer.receiveCard(card);
        int softCardValue = CardRank.ACE.getMaxValue();

        //then
        assertThat(dealer.calculateFinalScore()).isEqualTo(softCardValue);
    }

    @DisplayName("플레이어가 제시한 배팅 금액을 딜러가 보관한다.")
    @Test
    void 플레이어_제시_배팅금액을_저장() {
        //given
        int money = 10000;
        Player better = new Player("moda");
        Bet bet = new Bet(new Money(money), better);

        //when
        dealer.receiveBet(bet);

        //then
        assertThat(bet.getOwner()).isEqualTo(better);
    }

    @DisplayName("플레이어가 승리하면 플레이어의 배팅 금액을 돌려준다")
    @Test
    void 플레이어_승리시_배팅금액을_돌려준다() {
        //given
        int money = 10000;
        Player better = new Player("moda");
        Bet bet = new Bet(new Money(money), better);
        dealer.receiveBet(bet);

        //then
        Bet returnedBet = dealer.findBetByBetter(better);

        //then
        assertThat(returnedBet.equals(bet)).isTrue();
    }

    @DisplayName("패배한 플레이어의 베팅금액을 딜러의 것으로 만든다.")
    @Test
    void 패배한_플레이어의_배팅금액은_딜러의_것이_된다() {
        //given
        Player pobi = new Player("pobi");
        dealer.receiveBet(new Bet(new Money(10000), pobi));
        Player jason = new Player("jason");
        dealer.receiveBet(new Bet(new Money(20000), jason));

        //when
        dealer.updateBetOwnerFrom(jason);

        //then
        assertThat(dealer.findBetByBetter(pobi).getOwner()).isEqualTo(pobi);
        assertThat(dealer.findBetByBetter(jason).getOwner()).isEqualTo(dealer);
    }

    @DisplayName("10000원을 배팅한 플레이어는 승리하고, 20000원을 배팅한 플레이어는 패배했을 때 딜러는 10000원의 수익이 발생한다.")
    @Disabled
    @Test
    void 딜러는_10000원의_수익이_발생한다() {
        //given
        Player pobi = new Player("pobi");
        dealer.receiveBet(new Bet(new Money(10000), pobi));
        Player jason = new Player("jason");
        dealer.receiveBet(new Bet(new Money(20000), jason));

        //when
        dealer.updateBetOwnerFrom(jason);

        //then
        assertThat(dealer.calculateRevenue()).isEqualTo(10000);
    }

    @DisplayName("10000원을 배팅한 플레이어는 패배하고, 20000원을 배팅한 플레이어는 승리했을 때 딜러는 -10000원의 수익이 발생한다.")
    @Test
    void 딜러는_마이너스10000원의_수익이_발생한다() {
        //given
        Player pobi = new Player("pobi");
        Player jason = new Player("jason");
        dealer.receiveBet(new Bet(new Money(10000), pobi));
        dealer.receiveBet(new Bet(new Money(20000), jason));

        //when
        dealer.updateBetOwnerFrom(pobi);

        //then
        assertThat(dealer.calculateRevenue()).isEqualTo(-10000);
    }
}
