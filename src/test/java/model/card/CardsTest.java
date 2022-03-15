package model.card;

import static model.card.CardFace.ACE;
import static model.card.CardFace.KING;
import static model.card.CardSuit.DIAMOND;
import static model.card.CardSuit.HEART;
import static model.card.CardSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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
    void getSum() {
        final Card card1 = new Card(HEART, ACE);
        final Card card2 = new Card(DIAMOND, ACE);
        final Card card3 = new Card(SPADE, CardFace.NINE);
        final Cards cards = new Cards(List.of(card1, card2, card3));

        assertThat(cards.getSum()).isEqualTo(21);
    }
}
