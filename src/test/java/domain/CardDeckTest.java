package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {

    @Test
    @DisplayName("Card 덱은 52 장의 초기 개수를 가진다.")
    void createCardDeckSuccess() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.getSize()).isEqualTo(52);
    }
}
