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
        assertThat(card.getScore()).isEqualTo(Score.of(1));
    }

    @Test
    @DisplayName("점수 간의 덧셈을 올바르게 계산한다.")
    void additionTest() {
        // given
        Score score = Score.of(1);
        // when
        Score result = score.add(Score.of(2));
        // then
        assertThat(result).isEqualTo(Score.of(3));
    }

    @Test
    @DisplayName("에이스 추가 점수를 받아도 21점을 넘지 않는다면, 추가 점수를 반영한다.")
    void additionalAceTest() {
        // given
        Score score = Score.of(11);
        // when
        Score result = score.addAceScoreOnNotBust();
        // then
        assertThat(result).isEqualTo(Score.of(21));
    }

    @Test
    @DisplayName("에이스 추가 점수를 받아 21점이 초과된다면, 추가 점수를 반영하지 않는다.")
    void additionalAceBustTest() {
        // given
        Score score = Score.of(12);
        // when
        Score result = score.addAceScoreOnNotBust();
        // then
        assertThat(result).isEqualTo(Score.of(12));
    }

    @Test
    @DisplayName("블랙잭 점수 여부를 판단한다.")
    void isBlackJackScoreTest() {
        // given
        Score notBlackJackScore = Score.of(20);
        Score blackJackScore = Score.of(21);
        // when, then
        assertThat(notBlackJackScore.isBlackJackScore()).isFalse();
        assertThat(blackJackScore.isBlackJackScore()).isTrue();
    }

    @Test
    @DisplayName("버스트를 점수로 확인한다.")
    void isBustedTest() {
        // given
        Score notBustedScore = Score.of(21);
        Score bustedScore = Score.of(22);
        // when, then
        assertThat(notBustedScore.isBusted()).isFalse();
        assertThat(bustedScore.isBusted()).isTrue();
    }
}
