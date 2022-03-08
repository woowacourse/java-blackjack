package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class goodTest {

    @Test
    void 테스트() {
        assertThat(",   ,".split(","))
                .isEqualTo(List.of("", "   ", ""));
    }
}
