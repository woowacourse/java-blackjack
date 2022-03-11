package model.card;

import static model.card.CardFace.ACE;
import static model.card.CardFace.KING;
import static model.card.CardSuit.DIAMOND;
import static model.card.CardSuit.HEART;
import static model.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.Result;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    void duplicatedCards() {
        assertThatThrownBy(() -> new Cards(List.of(new Card(SPADE, ACE), new Card(SPADE, ACE))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드를 받을 수 없습니다.");
    }

    @Test
    void addDuplicatedCards() {
        Cards cards = new Cards((List.of(new Card(SPADE, ACE), new Card(SPADE, KING))));
        assertThatThrownBy(() -> cards.addCard(new Card(SPADE, ACE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드를 받을 수 없습니다.");
    }

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
        final Card card2 = new Card(HEART, ACE);
        final Card card3 = new Card(DIAMOND, ACE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.canReceiveCard()).isTrue();
    }

    @Test
    void cannotReceiveCardWithAce() {
        final Card card1 = new Card(HEART, ACE);
        final Card card2 = new Card(DIAMOND, ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));
        assertThat(cards.canReceiveCard()).isFalse();
    }

    @Test
    void getSum() {
        final Card card1 = new Card(HEART, ACE);
        final Card card2 = new Card(DIAMOND, ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.getSum()).isEqualTo(21);
    }

    @Test
    void match() {
        final Card card1 = new Card(HEART, ACE);
        final Card card2 = new Card(DIAMOND, ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards bigger = new Cards(List.of(card1, card2, card3));

        final Card card4 = new Card(SPADE, CardFace.EIGHT);
        final Card card5 = new Card(HEART, ACE);
        final Card card6 = new Card(DIAMOND, ACE);
        final Cards smaller = new Cards(List.of(card4, card5, card6));

        assertThat(bigger.getResult(smaller)).isEqualTo(Result.WIN);
    }

    @Test
    void loseMatch() {
        final Card card4 = new Card(SPADE, CardFace.EIGHT);
        final Card card5 = new Card(HEART, ACE);
        final Card card6 = new Card(DIAMOND, ACE);
        final Cards notBust = new Cards(List.of(card4, card5, card6));

        final Card card1 = new Card(HEART, CardFace.KING);
        final Card card2 = new Card(DIAMOND, CardFace.JACK);
        final Card card3 = new Card(SPADE, CardFace.QUEEN);
        final Cards bust = new Cards(List.of(card1, card2, card3));
        assertThat(bust.getResult(notBust)).isEqualTo(Result.LOSE);
    }

    @Test
    void drawMatch() {
        final Card card1 = new Card(HEART, CardFace.KING);
        final Card card2 = new Card(DIAMOND, CardFace.JACK);
        final Card card3 = new Card(SPADE, CardFace.QUEEN);
        final Cards bust = new Cards(List.of(card1, card2, card3));

        final Card card4 = new Card(SPADE, CardFace.KING);
        final Card card5 = new Card(HEART, CardFace.JACK);
        final Card card6 = new Card(DIAMOND, CardFace.QUEEN);
        final Cards otherBust = new Cards(List.of(card4, card5, card6));
        assertThat(bust.getResult(otherBust)).isEqualTo(Result.DRAW);
    }
}
