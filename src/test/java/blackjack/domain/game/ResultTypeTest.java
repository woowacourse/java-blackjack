package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    private static final int TYPES_LENGTH = 3;

    @DisplayName("WIN, LOSE, DRAW 3개의 인스턴스가 생성된다.")
    @Test
    void init() {
        int actual = ResultType.values().length;
        assertThat(actual).isEqualTo(TYPES_LENGTH);
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
