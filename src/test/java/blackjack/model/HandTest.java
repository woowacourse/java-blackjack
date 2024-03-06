package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // when
        Hand hand = new Hand((number) -> 1);

        // then
        assertThat(hand.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("카드 합 계산은 카드 숫자를 기본으로 한다")
    void calculateCardsTotalTest() {
        // given
        Hand hand = new Hand((number) -> 0);

        // when
        int actualTotal = hand.calculateCardsTotal();

        // then
        int expectedTotal = Denomination.TWO.getScore() + Denomination.TWO.getScore();
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }
}
