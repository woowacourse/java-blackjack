package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class DeckTest {

    @Test
    @DisplayName("Deck의 card 수는 52장 이어야 한다.")
    void createDeck() {
        Deck deck = new Deck();

        assertThat(deck.getCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("Deck에서 카드를 한장 빼서 반환한다.")
    void pickCardFromDeck() {
        Deck deck = new Deck();

        Card card = deck.pickCard();
        assertThat(deck.getCards().size()).isEqualTo(51);
    }

}
