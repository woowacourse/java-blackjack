package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackCardTypeTest {

    @DisplayName("카드 종류 같으면 equals이 true리턴하는지 테스트")
    @Test
    void equalsTest() {
        Card card = Card.generateCard(BlackjackCardType.DIAMOND_10);
        assertThat(card.equals(Card.generateCard(BlackjackCardType.DIAMOND_10))).isTrue();
    }

    @DisplayName("카드 종류 다르면 equals이 false리턴하는지 테스트")
    @Test
    void equalsTest2() {
        Card card = Card.generateCard(BlackjackCardType.DIAMOND_10);
        assertThat(card.equals(Card.generateCard(BlackjackCardType.HEART_10))).isFalse();
    }
}
