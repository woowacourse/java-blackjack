package blackjack.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.THREE), new Card(Suit.SPADE, Rank.FOUR)));
    }

    @Test
    @DisplayName("스코어 클래스 생성 확인")
    void createScore() {
        assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    @DisplayName("점수 합산 확인")
    void checkSumPoint() {
        assertThat(score.value()).isEqualTo(7);
    }

    @Test
    @DisplayName("score card 2개일 때 점수 합산")
    public void checkSumScoreCardsPoint() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.EIGHT), new Card(Suit.SPADE, Rank.TWO)));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(10);
    }

    @Test
    @DisplayName("score card 3개일 때 점수 합산")
    public void checkSumScoreThreeCardsPoint() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.EIGHT), new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.JACK)));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(20);
    }

    @Test
    @DisplayName("ace 4개 일때 점수 확인")
    public void checkPointsForFourAces() {
        score = new Score(
                Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.CLUB, Rank.ACE), new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.HEART, Rank.ACE)));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(14);
    }

    @Test
    @DisplayName("ace 3개 일때 13점 점수 확인")
    public void checkPointsForThreeAces() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE), new Card(Suit.DIAMOND, Rank.TWO)));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(15);
    }

    @Test
    @DisplayName("ace 2개 일때 12점 점수 확인")
    public void checkPointsForTwoAces() {
        score = new Score(
                Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.CLUB, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.NINE)));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(21);
    }

    @Test
    @DisplayName("ace 2개 일때 2점 점수 확인")
    public void checkPointsForTwoAcesOverLimit() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.CLUB, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.JACK)));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(12);
    }

    @Test
    @DisplayName("blackjack 카드 확인")
    public void checkBlackjackCards() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.SPADE, Rank.JACK)));
        assertThat(score.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("3개 카드의 합이 21일 때 블랙잭이 아님을 확인")
    public void checkNoBlackjackWithThreeCards() {
        score = new Score(Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.SIX)));
        assertThat(score.isBlackjack()).isFalse();
    }
}
