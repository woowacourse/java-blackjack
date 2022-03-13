package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Deck에서 정상적으로 카드를 하나 뽑는다.")
    void pick_card() {
        Deck fixDeck = new FixDeck();
        Card pickedCard = fixDeck.pick();
        Card card = new Card(CardNumber.TEN, Type.SPADE);

        assertThat(pickedCard).isEqualTo(card);
    }
}
