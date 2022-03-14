package blackjack.domain.card.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("pick 메서드는 Deck에서 카드를 한 장 뽑는다.")
    void pick_card() {
        Deck deck = new OnlyTenSpadePickDeck();
        Card pickedCard = deck.pick();
        Card card = new Card(CardNumber.TEN, CardType.SPADE);

        assertThat(pickedCard).isEqualTo(card);
    }

    @Test
    @DisplayName("pickTwoCards 메서드는 카드를 두 장 뽑는다.")
    void pick_two_cards() {
        Deck deck = new OnlyTenSpadePickDeck();
        List<Card> twoCards = deck.pickTwoCards();

        assertThat(twoCards).hasSize(2);
    }
}
