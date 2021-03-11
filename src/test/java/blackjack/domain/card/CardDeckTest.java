package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 덱 테스트")
    void drawTest() {
        CardDeck cardDeck = new CardDeck();

        boolean isCard = cardDeck.draw() instanceof Card;

        assertThat(isCard).isTrue();
    }
}
