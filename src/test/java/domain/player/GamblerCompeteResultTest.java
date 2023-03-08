package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static domain.player.GamblerCompeteResult.LOSE;
import static domain.player.GamblerCompeteResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("GamblerCompeteResult 는")
class GamblerCompeteResultTest {

    @Test
    void WIN_은_win_이다() {
        // when & then
        assertThat(WIN.isWin()).isTrue();
    }

    @ParameterizedTest(name = "WIN 이 아닌 경우(ex: {0}) win()은 false 이다")
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"WIN"})
    void WIN_이_아닌_경우_win_이_아니다(final GamblerCompeteResult gamblerCompeteResult) {
        // when & then
        assertThat(gamblerCompeteResult.isWin()).isFalse();
    }

    @Test
    void LOSE_는_lose_이다() {
        // when & then
        assertThat(LOSE.isLose()).isTrue();
    }

    @ParameterizedTest(name = "LOSE 가 아닌 경우(ex: {0}) lose()은 false 이다")
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"LOSE"})
    void LOSE_가_아닌_경우_lose_가_아니다(final GamblerCompeteResult gamblerCompeteResult) {
        // when & then
        assertThat(gamblerCompeteResult.isLose()).isFalse();
    }
}