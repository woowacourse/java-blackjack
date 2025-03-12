package object.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class GameResultTest {

    @ParameterizedTest
    @EnumSource(GameResult.class)
    void 배틀_결과_생성_테스트(GameResult gameResult) {
        Assertions.assertThat(gameResult).isInstanceOf(GameResult.class);
    }
}
