package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerCardsTest {

    @Test
    @DisplayName("카드를 받을 수 있다")
    void addCardTest() {
        PlayerCards playerCards = new PlayerCards();
        Card card = new Card(CardSuit.SPADE, CardNumber.ACE);

        playerCards.add(card);

        assertThat(playerCards.toList()).contains(card);
    }
}
