package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @Test
    @DisplayName("덱 생성 검사")
    void createDeck() {
        Deck deck = new Deck(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.SPADE, CardValue.ACE)));

        assertThat(deck.getCards()).contains(
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.SPADE, CardValue.ACE));
    }
}
