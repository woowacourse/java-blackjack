import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardsSumTest {

    @Test
    void cards_sum_without_ace() {
        assertThat(Cards.sumScore("2,3,J,Q")).isEqualTo(25);
    }

    @Test
    void cards_sum_with_ace() {
        assertThat(Cards.sumScore("2,3,A,J,Q")).isEqualTo(26);
        assertThat(Cards.sumScore("2,3,A")).isEqualTo(16);
    }
}
