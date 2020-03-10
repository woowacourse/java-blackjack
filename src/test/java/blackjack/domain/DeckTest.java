package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

    @Test
    @DisplayName("카드 꺼내기")
    void pick() {
        List<Card> cards = CardFactory.generate();
        Deck deck = new Deck(cards);
        assertThat(deck.pick()).isInstanceOf(Card.class);
    }
}
