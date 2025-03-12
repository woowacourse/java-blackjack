package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandStateTest {

    @DisplayName("블랙잭의 손패가 있을 때 HandState는 블랙잭이 된다.")
    @Test
    void test_BlackjackState() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.SPADE, CardRank.KING));
        cardHand.add(new Card(CardSuit.SPADE, CardRank.ACE));

        // when
        HandState handState = HandState.from(cardHand);

        // then
        assertThat(handState).isEqualTo(HandState.BLACKJACK);
    }

    @DisplayName("버스트된 손패가 있을 때 HandState는 블랙잭이 된다.")
    @Test
    void test_BustState() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.SPADE, CardRank.KING));
        cardHand.add(new Card(CardSuit.SPADE, CardRank.JACK));
        cardHand.add(new Card(CardSuit.SPADE, CardRank.TWO));

        // when
        HandState handState = HandState.from(cardHand);

        // then
        assertThat(handState).isEqualTo(HandState.BUST);
    }

    @DisplayName("버스트거나 블랙잭 상태가 아닌손패는 NORMAL한 State이다.")
    @Test
    void test_NormalState() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.SPADE, CardRank.KING));
        cardHand.add(new Card(CardSuit.SPADE, CardRank.JACK));

        // when
        HandState handState = HandState.from(cardHand);

        // then
        assertThat(handState).isEqualTo(HandState.NORMAL);
    }
}