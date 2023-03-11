package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BlackJackScore 은")
class BlackJackScoreTest {

    @ParameterizedTest(name = "0 이상의 점수를 받아 생성된다.")
    @ValueSource(ints = {0, 1, 21, 100})
    void 특정_점수를_통해_생성된다(final int value) {
        // when
        final BlackJackScore blackJackScore = BlackJackScore.of(value);

        // then
        assertThat(blackJackScore.value()).isEqualTo(value);
    }

    @ParameterizedTest(name = "음수일 수 없다")
    @ValueSource(ints = {-1, -10})
    void 음수일_수_없다(final int value) {
        // when & then
        assertThatThrownBy(() -> BlackJackScore.of(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "점수가 같으면 동등하다")
    @ValueSource(ints = {0, 1, 10, 21, 25})
    void 점수가_같으면_동등하다(final int value) {
        // given
        final BlackJackScore blackJackScore1 = BlackJackScore.of(value);
        final BlackJackScore blackJackScore2 = BlackJackScore.of(value);

        // when & then
        assertThat(blackJackScore1).isEqualTo(blackJackScore2);
    }

    @Nested
    class 블랙잭_점수_테스트 {

        @Test
        void 점수가_21_이면_블랙잭_점수이다() {
            // given
            final BlackJackScore blackJackScore = BlackJackScore.of(21);

            // when & then
            assertThat(blackJackScore.isBlackJackScore()).isTrue();
        }

        @ParameterizedTest(name = "점수가 21점이 아니면 (ex: {0} 점) 블랙잭 점수가 아니다")
        @ValueSource(ints = {0, 1, 20, 22})
        void 점수가_21점이_아니면_블랙잭_점수가_아니다(final int value) {
            // given
            final BlackJackScore blackJackScore = BlackJackScore.of(value);

            // when & then
            assertThat(blackJackScore.isBlackJackScore()).isFalse();
        }
    }

    @Nested
    class 버스트_점수_테스트 {

        @ParameterizedTest(name = "점수가 21점 초과라면 버스트이다")
        @ValueSource(ints = {22, 23, 100})
        void 점수가_21_점_초과라면_버스트이다(final int value) {
            // given
            final BlackJackScore blackJackScore = BlackJackScore.of(value);

            // when & then
            assertThat(blackJackScore.isBust()).isTrue();
        }

        @ParameterizedTest(name = "점수가 21점 이하라면 버스트가 아니다")
        @ValueSource(ints = {21, 20, 10, 1, 0})
        void 점수가_21점_이하라면_버스트가_아니다(final int value) {
            // given
            final BlackJackScore blackJackScore = BlackJackScore.of(value);

            // when & then
            assertThat(blackJackScore.isBust()).isFalse();
        }

    }

    @ParameterizedTest(name = "버스트에 상관없이 점수가 큰 수가 더 크다. 예를 들어 {0} 은 {1} 보다 높은 점수이다")
    @CsvSource(value = {
            "1 > 0",
            "10 > 9",
            "19 > 17",
            "21 > 20",
            "25 > 21",
    }, delimiterString = " > ")
    void 점수_비교를_할_수_있다(final int large, final int small) {
        // when & then
        assertThat(BlackJackScore.of(large).isLargerThan(BlackJackScore.of(small)))
                .isTrue();
    }

    @Nested
    class plusTenIfNotBust_테스트 {

        @ParameterizedTest(name = "plusTenIfNotBust 는 10을 더했을 때 버스트되지 않는 경우 10을 더한다")
        @CsvSource(value = {
                "0 -> 10",
                "1 -> 11",
                "10 -> 20",
                "11 -> 21",
        }, delimiterString = " -> ")
        void 점수에_10을_더했을_때_버스트되지_않는_경우_10을_더한다(final int before, final int plusTenIfNotBust) {
            // given
            final BlackJackScore beforeScore = BlackJackScore.of(before);
            final BlackJackScore plusTenIfNotBustScore = BlackJackScore.of(plusTenIfNotBust);

            // when & then
            assertThat(beforeScore.plusTenIfNotBust()).isEqualTo(plusTenIfNotBustScore);
        }

        @ParameterizedTest(name = "plusTenIfNotBust 는 10을 더했을 때 버스트되지 않는 경우 10을 더한다")
        @CsvSource(value = {
                "12 -> 12",
                "21 -> 21",
                "22 -> 22",
        }, delimiterString = " -> ")
        void 점수에_10을_더했을_때_버스트되는_경우_10을_더하지_않는다(final int before, final int plusTenIfNotBust) {
            // given
            final BlackJackScore beforeScore = BlackJackScore.of(before);
            final BlackJackScore plusTenIfNotBustScore = BlackJackScore.of(plusTenIfNotBust);

            // when & then
            assertThat(beforeScore.plusTenIfNotBust()).isEqualTo(plusTenIfNotBustScore);
        }

    }
}
