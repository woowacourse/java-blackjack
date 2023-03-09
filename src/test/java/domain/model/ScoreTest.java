package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.type.Letter;
import domain.type.Suit;
import domain.vo.Score;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("Socre 생성 테스트")
    public void testCreate() {
        //given
        //when
        //then
        assertDoesNotThrow(() -> Score.of(Cards.makeEmpty()));
    }

    @Test
    @DisplayName("에이스가 포함된 경우 Score 생성 테스트")
    public void testCreateWhenAceContains() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.SPADE, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.HEART, Letter.ACE));
        final Cards cards = new Cards(cardSet);

        //when
        final Score score = Score.of(cards);

        //then
        assertThat(score.getValue()).isEqualTo(14);
    }

    @Test
    @DisplayName("21 이하의 점수 생성을 테스트")
    public void testScoreOfUnder21() {
        //given
        final Set<Card> cardSet = Set.of(new Card(Suit.CLUB, Letter.ACE));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final int value = score.getValue();

        //then
        assertThat(value).isEqualTo(11);
    }

    @Test
    @DisplayName("ACE가 포함된 21점 생성을 테스트")
    public void testScoreOf21() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.DIAMOND, Letter.TEN));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final int value = score.getValue();

        //then
        assertThat(value).isEqualTo(21);
    }

    @Test
    @DisplayName("ACE 2개가 포함된 21점 생성을 테스트")
    public void testScoreContainsTwoAceOf21() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.CLUB, Letter.NINE));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final int value = score.getValue();

        //then
        assertThat(value).isEqualTo(21);
    }

    @Test
    @DisplayName("버스트가 아닌지 테스트")
    public void testIsBustFalse() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.CLUB, Letter.NINE));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final boolean result = score.isBust();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("버스트가 맞는지 테스트")
    public void testIsBustTrue() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.SPADE, Letter.ACE),
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.CLUB, Letter.NINE));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final boolean result = score.isBust();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("스탠드가 맞는지 테스트")
    public void testIsStandTrue() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.SPADE, Letter.ACE));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final boolean result = score.isStand();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("스탠드가 아닌지 테스트")
    public void testIsStandFalse() {
        //given
        final Set<Card> cardSet = Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.DIAMOND, Letter.TEN),
            new Card(Suit.SPADE, Letter.ACE),
            new Card(Suit.SPADE, Letter.TWO));
        final Cards cards = new Cards(cardSet);
        final Score score = Score.of(cards);

        //when
        final boolean result = score.isStand();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("스코어가 비교 대상보다 더 작은지 테스트")
    public void testIsLessThan() {
        //given
        Score score = Score.of(1);
        Score comparedScore = Score.of(2);

        //when
        boolean result = score.lessThanOrEqualTo(comparedScore);

        //then
        assertThat(result).isTrue();
    }
}
