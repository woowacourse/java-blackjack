package model;

import static model.CardSuit.DIAMOND;
import static model.CardSuit.HEART;
import static model.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void cannotReceiveCard() {
        final Card card1 = new Card(SPADE, CardFace.QUEEN);
        final Card card2 = new Card(HEART, CardFace.QUEEN);
        final Card card3 = new Card(DIAMOND, CardFace.QUEEN);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.canReceiveCard()).isFalse();
    }

    @Test
    void canReceiveCard() {
        final Card card1 = new Card(SPADE, CardFace.QUEEN);
        final Card card2 = new Card(HEART, CardFace.QUEEN);
        final Cards cards = new Cards(List.of(card1, card2));

        assertThat(cards.canReceiveCard()).isTrue();
    }

    @Test
    void canReceiveCardWithAce() {
        final Card card1 = new Card(SPADE, CardFace.EIGHT);
        final Card card2 = new Card(HEART, CardFace.ACE);
        final Card card3 = new Card(DIAMOND, CardFace.ACE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.canReceiveCard()).isTrue();
    }

    @Test
    void cannotReceiveCardWithAce() {
        final Card card1 = new Card(HEART, CardFace.ACE);
        final Card card2 = new Card(DIAMOND, CardFace.ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));
        assertThat(cards.canReceiveCard()).isFalse();
    }

    @Test
    void getSum() {
        final Card card1 = new Card(HEART, CardFace.ACE);
        final Card card2 = new Card(DIAMOND, CardFace.ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.getSum()).isEqualTo(21);
    }
}
