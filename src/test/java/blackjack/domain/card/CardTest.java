package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("카드가 에이스인 경우 true를 반환한다")
    void isAceTrueTest() {
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        assertTrue(card.isAce());
    }

    @Test
    @DisplayName("카드가 에이스가 아닌 경우 false를 반환한다")
    void isAceFalseTest() {
        Card card = new Card(CardSuit.HEART, CardNumber.KING);

        assertFalse(card.isAce());
    }
}
