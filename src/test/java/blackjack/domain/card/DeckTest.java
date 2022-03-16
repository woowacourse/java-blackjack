package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("카드 1장 반환 확인")
    void pickCard() {
        Deck deck = new Deck();
        deck.draw();
        assertThat(deck.getSize()).isEqualTo(51);
    }

    @Test
    @DisplayName("덱 생성 시, 52장의 카드가 추가된다.")
    void getSize() {
        Deck deck = new Deck();
        assertThat(deck.getSize()).isEqualTo(52);
    }
}