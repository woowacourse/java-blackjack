package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class DeckTest {

    private final Deck deck = new Deck();

    @DisplayName("52개의 카드를 생성할 수 있다.")
    @Test
    void createCardsTest() {
        Card card = new Card(Value.ACE, Shape.SPADE);
        assertThat(deck.contains(card)).isTrue();
    }

    @DisplayName("카드를 한 장씩 반환받을 수 있다.")
    @Test
    void receiveCardTest() {
        assertThat(deck.getCard()).isEqualTo(new Card(Value.KING, Shape.HEART));
        assertThat(deck.getCard()).isEqualTo(new Card(Value.KING, Shape.DIAMOND));
    }
}
