package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalDeckTest {
    @Test
    @DisplayName("전체 덱에서 카드를 가져온다.")
    void getNewCardTest() {
        Card card1 = new Card(Shape.CLOVER, Number.ACE);
        Card card2 = new Card(Shape.CLOVER, Number.TEN);
        TotalDeck totalDeck = new TotalDeck(List.of(card1, card2));

        Card actualCard = totalDeck.getNewCard();

        assertThat(actualCard).isEqualTo(card2);
    }
}
