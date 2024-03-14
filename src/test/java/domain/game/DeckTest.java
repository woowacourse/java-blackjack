package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.game.deck.Deck;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @DisplayName("하나의 카드를 뽑는다.")
    @Test
    void pickCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.ACE, Suit.CLOVER));
        cards.add(new Card(Denomination.FIVE, Suit.DIAMOND));
        cards.add(new Card(Denomination.EIGHT, Suit.HEART));

        Deck createdDeck = new Deck(cards);
        Card picked = createdDeck.pick();
        assertThat(picked).isEqualTo(new Card(Denomination.ACE, Suit.CLOVER));
    }
}
