package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {

    @Test
    @DisplayName("생성자 테스트")
    void Names() {
        assertThat(new Names("jamie, ravie")).isInstanceOf(Names.class);

        assertThatThrownBy(() -> new Names((List<Name>) null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Empty or null names exception.");
        assertThatThrownBy(() -> new Names(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Empty or null names exception.");
    }

}
