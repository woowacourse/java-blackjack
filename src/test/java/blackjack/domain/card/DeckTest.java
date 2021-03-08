package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @DisplayName("Deck 객체를 생성한다.")
    @Test
    public void createDeck() {
        Deck deck = new Deck();

        assertThat(deck).isInstanceOf(Deck.class);
    }

    @DisplayName("덱에서 카드 두 장을 꺼낸다.")
    @Test
    public void popTwo() {
        Deck deck = new Deck();
        Cards cards = deck.popTwo();

        assertThat(cards.cards()).hasSize(2);
    }

    @DisplayName("덱에서 카드 한 장을 꺼낸다.")
    @Test
    public void popOne() {
        Deck deck = new Deck();
        Cards cards = deck.popOne();

        assertThat(cards.cards()).hasSize(1);
    }
}
