package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Dealer dealer = new Dealer();
        Card card = new Card(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        dealer.receiveCard(card);
        List<Card> dealerDeck = dealer.getCards();

        //then
        assertThat(dealerDeck).containsExactly(card);
    }

    @DisplayName("딜러의 카드 개수를 확인한다.")
    @Test
    void deckSize() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.FIVE));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));

        //when
        int deckSize = dealer.deckSize();

        //then
        assertThat(deckSize).isEqualTo(2);
    }

    @DisplayName("딜러의 점수를 계산한다.")
    @Test
    void calculateScore() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.FIVE));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));

        //when
        int totalScore = dealer.calculateScore().getScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }

    @DisplayName("덱이 버스트인 경우 덱에 있는 Ace 카드의 값을 1로 계산한다.")
    @Test
    void calculateScoreByBust() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.NINE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        //when
        int score = dealer.calculateScore().getScore();

        //then
        assertThat(score).isEqualTo(12);
    }

    @DisplayName("덱이 버스트 이고 Ace 카드가 여러장일 때, 버스트가 아니게 되는 최소한의 Ace의 카드만 값을 1로 계산한다.")
    @Test
    void calculateScoreByTwoAce() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.SEVEN));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        //when
        int score = dealer.calculateScore().getScore();

        //then
        assertThat(score).isEqualTo(21);
    }

    @DisplayName("딜러의 스코어가 17 미만이라면 히트할 수 있다.")
    @Test
    void canHitByExpectedTrue() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SIX));

        //when, then
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 스코어가 17 이상이라면 히트할 수 없다.")
    @Test
    void canHitByExpectedFalse() {
        //given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.CLOVER, CardNumber.SEVEN));

        //when, then
        assertThat(dealer.canHit()).isFalse();
    }
}
