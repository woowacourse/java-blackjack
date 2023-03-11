package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

class DeckTest {
    @Test
    @DisplayName("Deck이 정상적으로 생성된다.")
    void initializingDeck() {
        assertThatNoException()
                .isThrownBy(() -> Deck.from(cards -> cards));
    }
    
    @Test
    @DisplayName("Deck에서 draw하면 가장 늦게 push 된 카드를 가져온다.")
    void draw() {
        Deck deck = Deck.from(cards -> cards);
        Card card = deck.draw();
        
        assertThat(card).isEqualTo(new Card(Shape.DIAMOND, Number.JACK));
    }
}