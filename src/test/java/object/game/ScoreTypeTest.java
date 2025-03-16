package object.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ScoreTypeTest {
    @ParameterizedTest
    @EnumSource(ScoreType.class)
    void ScoreType_생성_테스트(ScoreType scoreType) {
        Assertions.assertThat(scoreType).isInstanceOf(ScoreType.class);
    }
}
