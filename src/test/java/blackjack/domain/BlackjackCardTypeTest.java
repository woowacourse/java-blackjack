package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackCardTypeTest {

    @DisplayName("같은 종류 카드 equals true인지 확인하는 테스트")
    @Test
    void equalsTest() {
        Card card = Card.generateCard(BlackjackCardType.DIAMOND_10);
        assertThat(card.equals(Card.generateCard(BlackjackCardType.DIAMOND_10))).isTrue();
    }

    @DisplayName("다른 종류 카드 equals false인지 확인하는 테스트")
    @Test
    void equalsTest2() {
        Card card = Card.generateCard(BlackjackCardType.DIAMOND_10);
        assertThat(card.equals(Card.generateCard(BlackjackCardType.HEART_10))).isFalse();
    }
}
