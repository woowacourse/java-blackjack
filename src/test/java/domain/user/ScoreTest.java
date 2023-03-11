package domain.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.CloverCard;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("블랙잭 카드 점수는 ")
class ScoreTest {
    @Test
    @DisplayName("카드의 denomination 값을 이용해 계산한다.")
    void createTest() {
        assertDoesNotThrow(() -> new Score(CloverCard.CLOVER_EIGHT.getScore()));
    }

    @Test
    @DisplayName("카드 숫자 값들이 주어지면 단순 합산으로 계산할 수 있다.")
    void calculateScoreTest() {
        //given
        List<Card> cards = List.of(CloverCard.CLOVER_TWO, CloverCard.CLOVER_THREE, CloverCard.CLOVER_FOUR, CloverCard.CLOVER_FIVE);

        //when
        Score score = new Score(cards);

        //then
        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }

    @ParameterizedTest
    @MethodSource("calculateScoreWithAceCase")
    @DisplayName("에이스를 포함하며 21 초과 시 에이스를 1점으로 계산한다.")
    void calculateScoreWithAceTest(List<Card> cards, int expected) {
        Score score = new Score(cards);

        Assertions.assertThat(score.getScore()).isEqualTo(expected);
    }

    static Stream<Arguments> calculateScoreWithAceCase() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_SEVEN,
                        CloverCard.CLOVER_EIGHT), 16),
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_ACE,
                        CloverCard.CLOVER_EIGHT), 20),
                Arguments.of(List.of(CloverCard.CLOVER_ACE, CloverCard.CLOVER_ACE, CloverCard.CLOVER_TEN,
                        CloverCard.CLOVER_NINE), 21)
        );
    }
}
