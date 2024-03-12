package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("카드의 점수를 정확하게 계산한다.")
    void calculateCardTest() {
        Card card = new Card(Shape.SPADE, Number.ACE);
        assertThat(card.getScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("점수 간의 덧셈을 올바르게 계산한다.")
    void additionTest() {
        // given
        Score score = new Score(1);
        // when
        Score result = score.add(new Score(2));
        // then
        assertThat(result).isEqualTo(new Score(3));
    }

    @Test
    @DisplayName("에이스 추가 점수를 받아도 21점을 넘지 않는다면, 추가 점수를 반영한다.")
    void additionalAceTest() {
        // given
        Score score = new Score(11);
        // when
        Score result = score.addAceScoreOnNotBust();
        // then
        assertThat(result).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("에이스 추가 점수를 받아 21점이 초과된다면, 추가 점수를 반영하지 않는다.")
    void additionalAceBustTest() {
        // given
        Score score = new Score(12);
        // when
        Score result = score.addAceScoreOnNotBust();
        // then
        assertThat(result).isEqualTo(new Score(12));
    }

}
