package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.Rank;
import blackjack.domain.deck.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱에 카드가 잘 추가된다.")
    void addCardTest() {
        Deck deck = new Deck();
        Card card = new Card(Shape.CLOVER, Rank.EIGHT);

        deck.addCard(card);
        Card result = deck.pickRandomCard();

        assertThat(result).isEqualTo(card);
    }

    @Test
    @DisplayName("랜덤 카드를 선택한다.")
    void pickRandomCardTest() {
        Deck deck = new Deck();
        Card card = new Card(Shape.HEART, Rank.EIGHT);

        deck.addCard(card);
        Card result = deck.pickRandomCard();

        assertThat(result).isEqualTo(card);
    }
}
