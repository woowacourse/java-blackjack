package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constants.Score;
import domain.constants.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
    @DisplayName("손에 있는 카드 정보를 반환한다.")
    @Test
    void getCards() {
        Hand hand = new Hand();
        hand.saveCard(new Card(Score.TEN, Shape.CLOVER));

        assertThat(hand.getCards().get(0)).isEqualTo(new Card(Score.TEN, Shape.CLOVER));
    }

}
