package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("덱은 52장의 카드를 가진다.")
    @Test
    void getSize() {
        Deck deck = new Deck();
        Assertions.assertThat(deck.getSize()).isEqualTo(52);
    }
}
