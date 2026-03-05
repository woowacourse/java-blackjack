package domain.player;

import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("카드가 에이스일 때 버스트가 아닐 경우 11이 더해지는지 검증")
    void 카드_에이스_11로_계산() {
        // given
        Score score = new Score();
        Card card = new Card("A", "하트");

        // when
        score.addValue(card);

        // then
        Assertions.assertThat(score.getScore()).isEqualTo(11);
    }

    @Test
    @DisplayName("카드가 에이스일 때 버스트일 경우 1이 더해지는지 검증")
    void 카드_에이스_1로_계산() {
        // given
        Score score = new Score();
        Card card1 = new Card("K", "하트");
        Card card2 = new Card("J", "하트");
        Card card3 = new Card("A", "하트");

        score.addValue(card1);
        score.addValue(card2);

        // when
        score.addValue(card3);

        // then
        Assertions.assertThat(score.getScore()).isEqualTo(21);
    }
}
