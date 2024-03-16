package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("중복없는 52장의 카드를 생성한다.")
    void generate() {
        final List<Card> values = Card.values();

        assertAll(
                () -> assertThat(values).hasSize(52),
                () -> assertThat(Set.copyOf(values)).hasSize(52));
    }
}
