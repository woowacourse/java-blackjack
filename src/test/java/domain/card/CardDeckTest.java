package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    @DisplayName("pick 메서드를 실행하면 카드가 출력된다.")
    void pickCard() {

        CardDeck cardDeck = new CardDeck();

        Card poppedCard = cardDeck.pickCard();

        assertThat(poppedCard).isInstanceOf(Card.class);
    }
}
