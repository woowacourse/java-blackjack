package model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardHandTest {

    @Test
    @DisplayName("ACE를 가지고 있는 지 테스트")
    void isAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.ACE, Type.CLUB));
        assertThat(cardHand.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드 점수 계산")
    void calculateScoreWithNoAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.THREE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.TWO, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithNoAce()).isEqualTo(11);
    }

    @Test
    @DisplayName("Ace를 가지고 있을때 - Ace 11점일때")
    void calculateScoreWithElevenAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.THREE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.ACE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithAce()).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace를 가지고 있을때 - Ace 1점일때")
    void calculateScoreWithOneAce_Test() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(new Card(Symbol.FIVE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.ACE, Type.DIAMOND));
        cardHand.addCard(new Card(Symbol.SIX, Type.DIAMOND));
        assertThat(cardHand.calculateScoreWithAce()).isEqualTo(12);
    }
}
