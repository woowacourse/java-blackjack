package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NamesTest {
    @Test
    @DisplayName("생성자 테스트")
    void Names() {
        Assertions.assertThat(new Names("jamie, ravie")).isInstanceOf(Names.class);

        Assertions.assertThatThrownBy(() -> new Names((List<Name>) null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Empty or null names exception.");
        Assertions.assertThatThrownBy(() -> new Names(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Empty or null names exception.");
    }

}