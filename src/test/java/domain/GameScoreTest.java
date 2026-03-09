package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameScoreTest {

    @Test
    void 점수를_빼면_새로운_객체가_생성된다() {
        // given
        GameScore score = new GameScore(20);
        // when
        GameScore result = score.minus(10);
        // then
        assertThat(result.getScore()).isEqualTo(10);
    }

    @Test
    void 다른_점수보다_큰지_판단할_수_있다() {
        // given
        GameScore score = new GameScore(20);
        GameScore other = new GameScore(19);
        // when
        boolean result = score.isBiggerThan(other);
        // then
        assertThat(result).isTrue();
    }

    @Test
    void 다른_점수보다_같거나_작은지_판단할_수_있다() {
        // given
        GameScore score = new GameScore(18);
        GameScore other = new GameScore(20);
        // when
        boolean result = score.isSameOrSmallerThan(other);
        // then
        assertThat(result).isTrue();
    }

    @Test
    void 같은_점수인지_판단할_수_있다() {
        // given
        GameScore score = new GameScore(21);
        GameScore other = new GameScore(21);
        // when
        boolean result = score.isSame(other);
        // then
        assertThat(result).isTrue();
    }

    @Test
    void 점수가_21을_초과하면_버스트이다() {
        // given
        GameScore score = new GameScore(22);
        // when
        boolean result = score.isBust();
        // then
        assertThat(result).isTrue();
    }

    @Test
    void 점수가_21이하면_버스트가_아니다() {
        // given
        GameScore score = new GameScore(21);
        // when
        boolean result = score.isBust();
        // then
        assertThat(result).isFalse();
    }

    @Test
    void 에이스가_있고_21을_초과하면_참을_반환한다() {
        // given
        GameScore score = new GameScore(22);
        int aceCount = 1;
        // when
        boolean result = score.isBustWithAce(aceCount);
        // then
        assertThat(result).isTrue();
    }

    @Test
    void Ace가_없으면_21을_초과해도_에이스를_포함하는_버스트가_아니다() {
        // given
        GameScore score = new GameScore(22);
        int aceCount = 0;
        // when
        boolean result = score.isBustWithAce(aceCount);
        // then
        assertThat(result).isFalse();
    }
}
