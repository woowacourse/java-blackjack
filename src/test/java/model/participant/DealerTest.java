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
}
