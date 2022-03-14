package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardPatternTest {

    @Test
    @DisplayName("카드 문양을 4개 반환할 수 있다.")
    void patterns() {
        final List<CardPattern> cardPatterns = CardPattern.allPatterns();
        final int distinctCount = (int) cardPatterns.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(4);
    }
}
