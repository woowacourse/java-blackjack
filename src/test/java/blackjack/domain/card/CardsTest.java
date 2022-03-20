package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("에이스가 포함된 경우의 점수를 계산한다. - 에이스가 11로 계산")
    void calculateScoreWhenAceIs11Test() {
        Cards cards = new Cards(Arrays.asList(Card.valueOf(Shape.CLOVER, Number.TWO), Card.valueOf(Shape.CLOVER,Number.ACE)));
        int actual = cards.getScore();
        int expected = 13;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("에이스가 포함된 경우의 점수를 계산한다. - 에이스가 1로 계산")
    void calculateScoreWhenAceIs1Test() {
        Cards cards = new Cards(Arrays.asList(Card.valueOf(Shape.CLOVER,Number.JACK), Card.valueOf(Shape.HEART,Number.JACK), Card.valueOf(Shape.CLOVER,Number.ACE)));
        int actual = cards.getScore();
        int expected = 21;
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("에이스가 포함되지 않은 경우의 점수를 계산한다.")
    void calculateScore() {
        Cards cards = new Cards(Arrays.asList(Card.valueOf(Shape.CLOVER,Number.TWO), Card.valueOf(Shape.HEART,Number.JACK)));
        int actual = cards.getScore();
        int expected = 12;
        assertThat(actual).isEqualTo(expected);

    }
}
