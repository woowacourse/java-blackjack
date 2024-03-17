package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {

    @DisplayName("사용자의 점수를 계산한다.")
    @Test
    void calculateScore() {
        Hands hands = new Hands();
        hands.receive(new Card(Shape.HEARTS, Rank.KING));

        int score = hands.calculateScore();

        assertThat(score).isEqualTo(10);
    }

    @DisplayName("ace를 11로 결정한다.")
    @Test
    void calculateAceIsEleven() {
        Hands hands = new Hands();
        hands.receive(new Card(Shape.HEARTS, Rank.KING));
        hands.receive(new Card(Shape.HEARTS, Rank.ACE));

        int totalScore = hands.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(21);
    }

    @DisplayName("ace를 1로 결정한다.")
    @Test
    void calculateAceIsOne() {
        Hands hands = new Hands();
        hands.receive(new Card(Shape.HEARTS, Rank.KING));
        hands.receive(new Card(Shape.HEARTS, Rank.NINE));
        hands.receive(new Card(Shape.HEARTS, Rank.ACE));

        int totalScore = hands.calculateScore();

        Assertions.assertThat(totalScore).isEqualTo(20);
    }
}
