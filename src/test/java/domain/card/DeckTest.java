package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    
    @DisplayName("덱은 52장의 카드를 가진다.")
    @Test
    void getSize() {
        Deck deck = new Deck();
        assertThat(deck.getSize()).isEqualTo(52);
    }
    
    @DisplayName("덱은 카드를 뽑아서 반환한다.")
    @Test
    void draw() {
        Deck deck = new Deck();
        assertThat(deck.draw()).isNotNull();
        assertThat(deck.getSize()).isEqualTo(51);
    }
    
    @DisplayName("덱을 다 뽑으면 예외를 던진다.")
    @Test
    void drawException() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThat(deck.getSize()).isEqualTo(0);
        assertThatThrownBy(deck::draw).isInstanceOf(IllegalStateException.class);
    }
}
