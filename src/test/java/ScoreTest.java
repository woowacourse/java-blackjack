import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.stream;

import domain.Score.Score;
import domain.card.Hand;
import domain.card.Card;
import domain.card.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import util.ErrorMessage;

class ScoreTest {

    @Nested
    @DisplayName("getValue(): ")
    class GetValue {
        public static Stream<Arguments> getValue() {
            return Stream.of(
                    // ACE 미포함 총합을 반환한다.
                    Arguments.of(0, false,0),
                    Arguments.of(11, false, 11),
                    Arguments.of(21, false,21),
                    Arguments.of(22, false,22),
                    // ACE가 포함되고, 총합이 11 이하일 경우 - 총합에 10을 더하여 반환해야한다.
                    Arguments.of(11, true, 21),
                    Arguments.of(12, true, 12),
                    Arguments.of(21, true, 21)
            );
        }

        @ParameterizedTest
        @MethodSource
        void getValue(int scoreValue, boolean containsAce, int expected) {
            Score score = new Score(scoreValue, containsAce);

            assertThat(score.getValue()).isEqualTo(expected);
        }
    }


    @Nested
    @DisplayName("isBurst(): ")
    class IsBurst {
        @ParameterizedTest
        @DisplayName("총합이 21을 초과한다면 true 반환, 아니라면 false 반환한다")
        @CsvSource({
                "2,false",
                "21,false",
                "22,true"
        })
        void isBurst(int totalScore, boolean expected) {
            Score score = new Score(totalScore, false);

            assertThat(score.isBurst()).isEqualTo(expected);
        }
    }
}
