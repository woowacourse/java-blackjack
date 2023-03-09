package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    @DisplayName("카드를 한 장 받으면 받은 카드의 수는 한장이다.")
    void hitCardTest() {
        //gvien
        Card card = new Card(CardNumber.KING, CardSuit.DIAMOND);
        Hand hand = new Hand();

        //when
        hand.hitCard(card);

        //then
        assertThat(hand.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Ace카드를 가지고 있으면 true를 반환한다.")
    void hasAceCardReturnTrueTest() {
        //gien
        Card card = new Card(CardNumber.ACE, CardSuit.DIAMOND);
        Hand hand = new Hand();
        hand.hitCard(card);

        //when
        boolean result = hand.hasAceCard();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Ace카드를 가지고 있지 않으면 false를 반환한다.")
    void hasAceCardReturnFalseTest() {
        //given
        Card card = new Card(CardNumber.KING, CardSuit.DIAMOND);
        Hand hand = new Hand();
        hand.hitCard(card);

        //when
        boolean result = hand.hasAceCard();

        //then
        assertThat(result).isFalse();
    }
}