package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("처음 뽑은 두 개 카드의 합을 구한다.")
    void sumInitCards() {
        Cards cards = new Cards(List.of(
                new Card(2, Shape.CLUB),
                new Card(3, Shape.CLUB)
        ));

        cards.receive(new Card(1, Shape.CLUB));

        assertThat(cards.sumInitCards()).isEqualTo(5);
    }
}
