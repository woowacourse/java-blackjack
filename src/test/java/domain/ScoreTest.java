package domain;

import static domain.Score.BLACKJACK;
import static domain.Score.EIGHT;
import static domain.Score.FIVE;
import static domain.Score.FOUR;
import static domain.Score.ONE;
import static domain.Score.SEVEN;
import static domain.Score.SIX;
import static domain.Score.TEN;
import static domain.Score.THREE;
import static domain.Score.TWO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScoreTest {
    private final Score BUST_SCORE = new Score(22);

    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void 정상적인_입력은_올바른_값을_가져야_한다(int value) {
        Assertions.assertThat(new Score(value).value()).isEqualTo(value);
    }

    @Test
    void 블랙잭이면_참을_반환해야_한다() {
        Assertions.assertThat(BLACKJACK.isBlackjack()).isTrue();
    }

    @Test
    void 블랙잭이_아니면_거짓을_반환해야_한다() {
        Assertions.assertThat(TEN.isBlackjack()).isFalse();
    }

    @Test
    void 버스트이면_참을_반환해야_한다() {
        Assertions.assertThat(BUST_SCORE.isBust()).isTrue();
    }

    @Test
    void 버스트가_아니면_거짓을_반환해야_한다() {
        Assertions.assertThat(TEN.isBust()).isFalse();
    }

    @Test
    void 덧셈은_정상적으로_수행되어야_한다_성공() {
        Assertions.assertThat(ONE.add(TWO)).isEqualTo(THREE);
    }

    @Test
    void 덧셈은_정상적으로_수행되어야_한다_실패() {
        Assertions.assertThat(THREE.add(FOUR)).isNotEqualTo(SIX);
    }

    @Test
    void 뺼셈은_정상적으로_수행되어야_한다_성공() {
        Assertions.assertThat(SEVEN.sub(FOUR)).isEqualTo(THREE);
    }

    @Test
    void 뺼셈은_정상적으로_수행되어야_한다_실패() {
        Assertions.assertThat(SEVEN.sub(TWO)).isNotEqualTo(FOUR);
    }

    @Test
    void 초과를_비교할_수_있다_성공() {
        Assertions.assertThat(SEVEN.isGreaterThan(FIVE)).isTrue();
    }

    @Test
    void 초과를_비교할_수_있다_실패() {
        Assertions.assertThat(SEVEN.isGreaterThan(EIGHT)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 6, 5, 0})
    void 이상을_비교할_수_있다_성공(int value) {
        Assertions.assertThat(SEVEN.isGreaterThanOrEqualTo(value)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 9, 100})
    void 이상을_비교할_수_있다_실패(int value) {
        Assertions.assertThat(SEVEN.isGreaterThanOrEqualTo(value)).isFalse();
    }

    @Test
    @ValueSource()
    void 이하를_비교할_수_있다_성공() {
        Assertions.assertThat(SEVEN.isLessThanOrEqualTo(SEVEN)).isTrue();
    }

    @Test
    void 이하를_비교할_수_있다_실패() {
        Assertions.assertThat(SEVEN.isLessThanOrEqualTo(SIX)).isFalse();
    }

    @Test
    void 동등을_비교할_수_있다_성공() {
        Assertions.assertThat(SEVEN.isEqualTo(SEVEN)).isTrue();
    }

    @Test
    void 동등을_비교할_수_있다_실패() {
        Assertions.assertThat(SEVEN.isEqualTo(EIGHT)).isFalse();
    }

}
