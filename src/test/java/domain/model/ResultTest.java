package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Letter;
import domain.type.Suit;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("무승부 결과 테스트")
    public void testDecideDraw() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.SIX)));
        Score score = Score.of(cards);
        Score comparedScore = Score.of(cards);

        //when
        Result draw = Result.decide(score, comparedScore);

        //then
        assertThat(draw.getVictory()).isEqualTo(0);
        assertThat(draw.getDraw()).isEqualTo(1);
        assertThat(draw.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("승리 결과 테스트")
    public void testDecideVictory() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.SIX)));
        Score score = Score.of(cards);
        Cards comparedCards = new Cards(Set.of(new Card(Suit.SPADE, Letter.FIVE)));
        Score comparedScore = Score.of(comparedCards);

        //when
        Result draw = Result.decide(score, comparedScore);

        //then
        assertThat(draw.getVictory()).isEqualTo(1);
        assertThat(draw.getDraw()).isEqualTo(0);
        assertThat(draw.getDefeat()).isEqualTo(0);
    }

    @Test
    @DisplayName("한 점수에 대한 여러 점수와 비교 테스트")
    public void testDecideScores() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.SIX)));
        Score score1 = Score.of(cards);
        Score score2 = Score.of(cards);

        Cards comparedCards = new Cards(Set.of(new Card(Suit.SPADE, Letter.FIVE)));
        Score comparedScore = Score.of(comparedCards);

        //when
        Result result = Result.decide(comparedScore, List.of(score1, score2));

        //then
        assertThat(result.getVictory()).isEqualTo(0);
        assertThat(result.getDraw()).isEqualTo(0);
        assertThat(result.getDefeat()).isEqualTo(2);
    }
}
