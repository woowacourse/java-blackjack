package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("21 이하의 점수 생성을 테스트")
    public void testScoreOfUnder21() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.CLUB, Letter.ACE)));
        Score score = Score.of(cards);

        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(11);
    }

    @Test
    @DisplayName("ACE가 포함된 21점 생성을 테스트")
    public void testScoreOf21() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.CLUB, Letter.ACE), new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.DIAMOND, Letter.TEN)));
        Score score = Score.of(cards);
        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(21);
    }
}