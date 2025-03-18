package blackjack.model.participants;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.Hand;
import blackjack.model.cards.Rank;
import blackjack.model.cards.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러는 2장 묶음의 카드 핸드를 초기화할 수 있다")
    @Test
    void testDealerHand_Init() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();

        assertThat(dealer.getHandCards().size()).isEqualTo(2);
    }

    @DisplayName("딜러는 2장 묶음의 카드 핸드를 생성할 수 있다")
    @Test
    void testDealerHand_Produce() {
        Dealer dealer = new Dealer();
        Hand hand = dealer.produceHand();

        assertThat(hand.getHandSize()).isEqualTo(2);
    }

    @DisplayName("딜러는 카드를 1장씩 뽑을 수 있다")
    @Test
    void testDealerHand_Pull() {
        Dealer dealer = new Dealer();

        assertThat(dealer.pullCard()).isNotNull();
    }

    @DisplayName("딜러는 카드 1장을 추가할 수 있다")
    @Test
    void testDealerHand_Add() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();

        Card card = new Card(Rank.ACE, Suit.SPADES);
        dealer.addCardToHand(card);

        assertThat(dealer.getHandCards().size()).isEqualTo(3);
    }

    @DisplayName("딜러는 자신이 가진 카드 덱의 합을 계산할 수 있다")
    @Test
    void testDealerHandScore() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();

        dealer.calculateHandScore();

        assertThat(dealer.getHandScore()).isNotNull();
    }

    @DisplayName("딜러는 카드의 합이 16 이하이면 카드를 한장 더 받아야한다")
    @Test
    void testDealerHandScore_Hit() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();

        dealer.calculateHandScore();

        if (dealer.getHandScore() <= 16) {
            assertThat(dealer.canHit()).isTrue();
        }
    }

    @DisplayName("딜러는 카드의 합이 17 이상이면 카드를 더 받지 않는다")
    @Test
    void testDealerHandScore_Stand() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();

        dealer.calculateHandScore();

        if (dealer.getHandScore() > 16) {
            assertThat(dealer.canHit()).isFalse();
        }
    }

    @DisplayName("딜러는 자신의 배팅 금액을 갱신할 수 있다")
    @Test
    void testDealerWager() {
        Dealer dealer = new Dealer();
        dealer.updateWager(1, 1000);

        assertThat(dealer.getWager()).isEqualTo(1000);
    }
}
