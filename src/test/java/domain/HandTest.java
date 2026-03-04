package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import org.junit.jupiter.api.Test;

class HandTest {

    @Test
    void test() {
        // given
        Hand hand = new Hand();
        Deck deck = new Deck();
        Card card = deck.drawCard();

        // when
        hand.receiveCard(card);
        // then
        assertThat(hand.getCards().size()).isEqualTo(1);
    }

}
