package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

class GamerTest {

    @Test
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void additionalCardBustTest1() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("21초과인 경우 버스트된다")
    void additionalCardBustTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.QUEEN);
        Card card3 = new Card(CardType.CLOVER, CardNumber.EIGHT);
        Player player = new Player("Pobi");
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("버스트되지 않으면 Ace는 11로 계산한다")
    void aceNotBustTest1() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.ACE);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.getSumOfCards()).isEqualTo(21);
    }

    @Test
    @DisplayName("버스트되면 Ace는 1로 계산한다")
    void aceNotBustTest2() {
        Card card1 = new Card(CardType.CLOVER, CardNumber.JACK);
        Card card2 = new Card(CardType.CLOVER, CardNumber.TWO);
        Card card3 = new Card(CardType.CLOVER, CardNumber.ACE);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);
        dealer.addCard(card3);

        assertThat(dealer.getSumOfCards()).isEqualTo(13);
    }

    @Test
    void isBust() {
    }

    @Test
    void getSumOfCards() {
    }

    @Test
    void isBlackjack() {
    }

    @Test
    void drawCard() {
    }

    @Test
    void getFinalResult() {
    }

    @Test
    void canReceiveAdditionalCards() {
    }
}
