package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 52장을 반환할 수 있다.")
    void cards() {
        final List<Card> cards = Card.cards();
        final int distinctCount = (int) cards.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(52);
    }
}
