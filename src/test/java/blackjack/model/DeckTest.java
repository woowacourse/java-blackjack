package blackjack.model;

import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    @DisplayName("카드 덱을 생성한다.")
    void createDeck() {
        Deck deck = new Deck();
        Assertions.assertThat(deck.getDeck()).hasSize(52);
    }

    @Test
    @DisplayName("카드 한 세트(52장)을 다 소진할 경우 예외가 발생한다.")
    void throwErrorWhenNoCard() {
        Deck deck = new Deck();
        Assertions.assertThatThrownBy(() -> deck.distributeInitialCard(27))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("카드가 부족합니다.");
    }
}
