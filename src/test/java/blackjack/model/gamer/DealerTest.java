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
        Dealer dealer = Dealer.create();
        Card card = Card.of(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        dealer.receiveCard(card);
        List<Card> dealerDeck = dealer.allCards();

        //then
        assertThat(dealerDeck).containsExactly(card);
    }

    @DisplayName("딜러가 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Dealer dealer = Dealer.create();
        Card card = Card.of(CardPattern.CLOVER, CardNumber.FIVE);

        //when
        dealer.receiveCard(card);

        //then
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Dealer dealer = Dealer.create();
        Card card1 = Card.of(CardPattern.CLOVER, CardNumber.FIVE);
        Card card2 = Card.of(CardPattern.CLOVER, CardNumber.SEVEN);

        //when
        dealer.receiveCard(card1);
        dealer.receiveCard(card2);
        int totalScore = dealer.totalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }
}
