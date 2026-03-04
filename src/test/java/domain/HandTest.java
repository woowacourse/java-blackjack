package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    @DisplayName("점수 합계 테스트")
    void 정상_테스트_1() {
        List<Card> cards = List.of(Card.JACK, Card.TEN);
        Hand hand = new Hand(cards);

        int expected = 20;
        int actual = hand.calculateScore();

        assertThat(actual).isEqualTo(expected);
    }
}
