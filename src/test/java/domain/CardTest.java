package domain;

import domain.card.Card;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("중복없는 52장의 카드를 생성한다.")
    void generate() {
        final List<Card> values = Card.values();

        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(values).hasSize(52),
                () -> Assertions.assertThat(Set.copyOf(values)).hasSize(52));
    }
}
