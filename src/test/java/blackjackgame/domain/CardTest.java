package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {
    @DisplayName("스페이스 5카드가 5의 점수를 가지는지 확인한다")
    @Test
    void Should_ReturnFive_When_FiveCard() {
        Card card = new Card(Symbol.SPADE, CardValue.FIVE);
        assertThat(card.getScore()).isEqualTo(CardValue.FIVE.getScore());
    }
}
