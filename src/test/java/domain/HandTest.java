package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @Test
    @DisplayName("현재 패의 점수 합계를 반환한다.")
    void totalScore() {
        Hand hand = new Hand();
        hand.add(new Card(CardType.DIAMOND, CardNumber.TWO));
        hand.add(new Card(CardType.DIAMOND, CardNumber.FIVE));
        Assertions.assertThat(hand.getTotalScore())
                .isEqualTo(7);
    }
}
