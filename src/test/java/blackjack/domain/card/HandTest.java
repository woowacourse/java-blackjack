package blackjack.domain.card;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.FIVE;
import static blackjack.domain.card.Number.JACK;
import static blackjack.domain.card.Number.NINE;
import static blackjack.domain.card.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    void should_NotBust_When_HandHas_FIVE_NINE_ACE() {
        Hand hand = new Hand();
        Card card1 = new Card(SPADE, FIVE);
        Card card2 = new Card(SPADE, NINE);
        Card card3 = new Card(SPADE, ACE);
        hand.add(card1);
        hand.add(card2);
        assertThat(hand.isBust()).isFalse();
        hand.add(card3);
        assertThat(hand.isBust()).isFalse();
        assertThat(hand.getSum()).isEqualTo(15);
    }

    @DisplayName("손패는 카드를 받으면 마지막 위치에 저장한다.")
    @Test
    void should_addCard_At_LastIndex() {
        Hand hand = new Hand();
        Card card = new Card(SPADE, JACK);

        hand.add(card);

        List<Card> cards = hand.getCards();
        Card lastCard = cards.get(cards.size() - 1);
        assertThat(lastCard).isEqualTo(card);
    }

    @DisplayName("손패는 카드를 받으면 카드의 수가 1 증가한다.")
    @Test
    void should_HasSize_1Increased() {
        Hand hand = new Hand();
        int previousSize = hand.getCards().size();

        Card card = new Card(SPADE, JACK);
        hand.add(card);
        int currentSize = hand.getCards().size();

        assertThat(currentSize).isEqualTo(previousSize + 1);
    }
}
