package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {
    @Test
    @DisplayName("카드 점수 확인")
    void cardScoreTest() {
        Card card = new Card(CardNumber.EIGHT, Shape.CLOVER);
        assertThat(card.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("카드 에이스 확인")
    void aceCheckTest() {
        Card card = new Card(CardNumber.ACE, Shape.CLOVER);
        assertTrue(card.isAce());
    }
}