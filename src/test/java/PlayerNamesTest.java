import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerNamesTest {

    @DisplayName("이름은 중복을 허용하지 않는다.")
    @Test
    void createNamesFailTestByDuplication() {
        Assertions.assertThatThrownBy(() -> PlayerNames.from(List.of("pobi","pobi")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
