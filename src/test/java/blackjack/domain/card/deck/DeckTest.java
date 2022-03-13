package blackjack.domain.card.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("Deck에서 정상적으로 카드를 하나 뽑는다.")
    void pick_card() {
        Deck fixDeck = new FixDeck();
        Card pickedCard = fixDeck.pick();
        Card card = new Card(CardNumber.TEN, CardType.SPADE);

        assertThat(pickedCard).isEqualTo(card);
    }
}
