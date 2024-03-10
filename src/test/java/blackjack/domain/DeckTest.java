package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Cards.Card;
import blackjack.domain.Cards.Deck;
import blackjack.domain.Cards.Rank;
import blackjack.domain.Cards.Shape;
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

    @Test
    @DisplayName("카드의 개수를 확인한다.")
    void sizeTest() {
        Deck deck = new Deck();
        assertThat(deck.size()).isEqualTo(0);
    }
}
