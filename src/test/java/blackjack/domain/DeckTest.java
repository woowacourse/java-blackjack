package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(Arrays.asList(
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.SPADE, CardValue.ACE)));
    }

    @Test
    @DisplayName("덱 생성 검사")
    void createDeck() {
        assertThat(deck.getCards()).contains(
                Card.valueOf(Shape.DIAMOND, CardValue.ACE),
                Card.valueOf(Shape.SPADE, CardValue.ACE));
    }

    @Test
    @DisplayName("드로우 테스트")
    void draw() {
        Card card = deck.draw();
        assertThat(card).isSameAs(Card.valueOf(Shape.DIAMOND, CardValue.ACE));
        assertThat(deck.getCardQueue().size()).isEqualTo(1);
    }

    //    @Test
//    @DisplayName("덱이 소진되었을 경우 검증")
//    void draw() {
//
//    }
}
