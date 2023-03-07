package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("입력된 이름 값들은 ")
public class NamesTest {
    @Test
    @DisplayName("하나일 수 있다.")
    void createSingleNameTest() {
        Names singleName = new Names(List.of("pobi"));

        assertThat(singleName.getNames()).isEqualTo(List.of(new Name("pobi")));
    }
}
