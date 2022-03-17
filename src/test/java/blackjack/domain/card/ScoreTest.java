package blackjack.domain.card;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Suit.HEARTS;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 3, 4})
    void 불가능한_점수가_들어올_경우_예외처리(final int score) {
        assertThatThrownBy(() -> new Score(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("4이하의 점수는 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"21,true", "20,false", "22,false"})
    void 블랙잭_점수인지_확인(final int inputScore, final boolean expected) {
        final Score score = new Score(inputScore);
        assertThat(score.isBlackjackScore()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"22,true", "20,false", "21,false"})
    void 버스트_점수인지_확인(final int inputScore, final boolean expected) {
        final Score score = new Score(inputScore);
        assertThat(score.isBustScore()).isEqualTo(expected);
    }

    @Test
    void 카드의_최대점수로_생성() {
        final Cards cards = new Cards(List.of(Card.of(SPADES, A), Card.of(HEARTS, A)));
        final Score score = Score.createMaxScore(cards);

        assertThat(score).isEqualTo(new Score(22));
    }
}
