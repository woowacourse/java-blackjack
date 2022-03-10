package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    private static final int TYPES_LENGTH = 3;

    @DisplayName("애플리케이션 생성 시점에 WIN, LOSE, DRAW라는 3개의 인스턴스가 생성된다.")
    @Test
    void init() {
        ResultType[] resultTypes = ResultType.values();

        assertThat(resultTypes[0]).isEqualTo(ResultType.valueOf("WIN"));
        assertThat(resultTypes[1]).isEqualTo(ResultType.valueOf("LOSE"));
        assertThat(resultTypes[2]).isEqualTo(ResultType.valueOf("DRAW"));
        assertThat(resultTypes.length).isEqualTo(TYPES_LENGTH);
    }

    @DisplayName("WIN, LOSE, DRAW 인스턴스는 각각 승, 패, 무라는 이름을 갖는다.")
    @Test
    void getDisplayName() {
        List<ResultType> types = List.of(ResultType.values());
        List<String> names = List.of("승", "패", "무");

        for (int i = 0; i < TYPES_LENGTH; i++) {
            assertThat(types.get(i).getDisplayName())
                    .isEqualTo(names.get(i));
        }
    }
}
