package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("마지막에 추가된 카드를 뽑는다.")
    @Test
    void drawTest() {
        // given
        Card card1 = new Card(Symbol.DIAMOND, Rank.EIGHT);
        Card card2 = new Card(Symbol.CLOVER, Rank.ACE);

        List<Card> cards = List.of(card1, card2);
        Deck deck = new Deck(cards);

        // when
        Card drawedCard = deck.draw();

        // then
        assertThat(drawedCard).isEqualTo(card2);
    }
}
