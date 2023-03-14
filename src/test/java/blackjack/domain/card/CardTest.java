package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드를 생성한다")
    void createCardTest() {
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        assertAll(
                () -> assertThat(card.getSuit()).isEqualTo(CardSuit.HEART),
                () -> assertThat(card.getNumber()).isEqualTo(CardNumber.ACE)
        );
    }
}
