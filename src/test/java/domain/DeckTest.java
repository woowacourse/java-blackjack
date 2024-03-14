package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("전체 덱에서 카드를 가져온다.")
    void getNewCardTest() {
        Card card1 = new Card(Shape.CLUB, Number.ACE);
        Card card2 = new Card(Shape.CLUB, Number.TEN);
        Deck deck = new Deck(List.of(card1, card2));

        Card actualCard = deck.drawCard();

        assertThat(actualCard).isEqualTo(card2);
    }
}
