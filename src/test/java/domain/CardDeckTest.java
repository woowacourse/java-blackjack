package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("Card 1장 뽑기 기능 테스트")
    void pickCardTest() {
        // given
        CardDeck cardDeck = new CardDeck();
        Card card = cardDeck.pickCard();
        // when & then
        assertThat(card).isNotNull();
    }
}
