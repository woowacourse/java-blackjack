package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class ScoreTest {
    private static final int ACE_ADJUST_AMOUNT = 10;
    private static final int BUST_THRESHOLD = 21;

    @ParameterizedTest
    @EnumSource(Rank.class)
    void 에이스를_11로_계산했을_때_버스트가_되지_않는다면_총점에_11로_반영된다(Rank rank) {
        // given
        Card ace = new Card(Rank.ACE, Suit.CLOVER);
        List<Card> cards = List.of(
            ace,
            new Card(rank, Suit.DIAMOND)
        );
        int sum = ace.getValue() + rank.getValue();
        // when
        Score score = Score.from(cards);
        // then
        assertThat(score.value()).isEqualTo(sum + ACE_ADJUST_AMOUNT);
    }

    @ParameterizedTest
    @EnumSource(Rank.class)
    void 에이스를_11로_계산했을_때_버스트_되면_총점에_1로_반영된다(Rank rank) {
        // given
        Card ace = new Card(Rank.ACE, Suit.CLOVER);
        Card ten = new Card(Rank.TEN, Suit.HEART);
        List<Card> cards = List.of(
            ace,
            ten,
            new Card(rank, Suit.DIAMOND)
        );
        int sum = ace.getValue() + ten.getValue() + rank.getValue();
        // when
        Score score = Score.from(cards);
        // then
        assertThat(score.value()).isEqualTo(sum);
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, mode = Mode.EXCLUDE, names = "ACE")
    void 에이스가_포함되지_않은_경우_주어진_카드_점수의_합을_최종_점수로_확정한다(Rank rank) {
        // given
        List<Card> cards = List.of(new Card(rank, Suit.CLOVER));
        int sum = rank.getValue();
        // when
        Score score = Score.from(cards);
        // then
        assertThat(score.value()).isEqualTo(sum);
    }

    @Test
    void 점수가_임계점을_넘으면_버스트로_판단한다() {
        // given
        Score score = new Score(BUST_THRESHOLD + 1);
        // when & then
        AssertionsForClassTypes.assertThat(score.isBust()).isTrue();
    }

    @Test
    void 점수가_임계점_이하면_버스트로_판단하지_않는다() {
        // given
        Score score = new Score(BUST_THRESHOLD);
        // when & then
        AssertionsForClassTypes.assertThat(score.isBust()).isFalse();
    }
}