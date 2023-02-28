import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerNamesTest {

    @DisplayName("이름은 중복을 허용하지 않는다.")
    @Test
    void createNamesFailTestByDuplication() {
        Assertions.assertThatThrownBy(() -> PlayerNames.from(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "참여자 수는 1명 이상 8명 이하여야 합니다.")
    @ValueSource(ints = {0, 9})
    void createNamesFailTestByNumberOfPlayers(int count) {
        Assertions.assertThatThrownBy(() -> PlayerNames.from(createPlayerNamesByCount(count)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    List<String> createPlayerNamesByCount(int count) {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            names.add(String.valueOf(i));
        }

        return names;
    }
}
