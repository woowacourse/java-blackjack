package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.card.CardProperties;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 카드를 받는다.")
    @Test
    void receiveCard() {
        //given
        Dealer dealer = new Dealer();
        CardProperties cardProperties = new CardProperties(CardPattern.CLOVER, CardNumber.FIVE);
        Card card = new Card(cardProperties);

        //when
        dealer.receiveCard(card);
        List<Card> dealerDeck = dealer.getHandDeck();

        //then
        assertThat(dealerDeck).containsExactly(card);
    }

    @DisplayName("딜러가 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Dealer dealer = new Dealer();
        CardProperties cardProperties = new CardProperties(CardPattern.CLOVER, CardNumber.FIVE);
        Card card = new Card(cardProperties);

        //when
        dealer.receiveCard(card);

        //then
        assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 카드합을 확인한다.")
    @Test
    void calculateTotalScore() {
        //given
        Dealer dealer = new Dealer();
        CardProperties cardProperties1 = new CardProperties(CardPattern.CLOVER, CardNumber.FIVE);
        CardProperties cardProperties2 = new CardProperties(CardPattern.CLOVER, CardNumber.SEVEN);

        Card card = new Card(cardProperties1);
        Card card2 = new Card(cardProperties2);

        //when
        dealer.receiveCard(card);
        dealer.receiveCard(card2);
        int totalScore = dealer.calculateTotalScore();

        //then
        assertThat(totalScore).isEqualTo(12);
    }
}
