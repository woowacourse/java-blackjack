package domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("ACE를 11로 고려한 21 이하의 점수 생성을 테스트")
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
    @DisplayName("ACE를 1개를 1로 고려한 포함된 21점 생성을 테스트")
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

    @Test
    @DisplayName("ACE 2개를 1로 고려한 21점 생성을 테스트")
    public void testScoreContainsTwoAceOf21() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.CLUB, Letter.ACE), new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.TEN), new Card(Suit.CLUB, Letter.NINE)));
        Score score = Score.of(cards);
        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE 2개를 1로, 1개를 11로 고려한 21점 생성을 테스트")
    public void testScoreContainsThreeAceOf21() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.SPADE, Letter.ACE),
            new Card(Suit.CLUB, Letter.EIGHT)));
        Score score = Score.of(cards);
        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE 3개를 1로, 1개를 11로 고려한 21점 생성을 테스트")
    public void testScoreContainsFourAceOf21() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.HEART, Letter.ACE),
            new Card(Suit.CLUB, Letter.SEVEN)));
        Score score = Score.of(cards);
        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE 4개를 1로 고려한 21점 초과를 테스트")
    public void testScoreContainsFourAceOf24() {
        //given
        Cards cards = new Cards(Set.of(new Card(Suit.SPADE, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.HEART, Letter.ACE),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.DIAMOND, Letter.TEN)));
        Score score = Score.of(cards);
        //when
        int value = score.getValue();

        //then
        assertThat(value).isEqualTo(24);
    }
}
