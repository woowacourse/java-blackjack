package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardsTest {

    @Test
    @DisplayName("중복된 카드를 넣을 수 없다")
    void testCards() {
        Cards cards = new Cards();
        assertThatThrownBy(() ->
                cards.addAll(List.of(
                        new Card(CardNumber.JACK, CardShape.DIAMOND),
                        new Card(CardNumber.JACK, CardShape.DIAMOND)
                ))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Bust 상태라면 카드를 넣을 수 없다")
    void testBustCards() {
        Cards cards = new Cards();
        cards.addAll(List.of(
                new Card(CardNumber.JACK, CardShape.DIAMOND),
                new Card(CardNumber.QUEEN, CardShape.DIAMOND),
                new Card(CardNumber.KING, CardShape.DIAMOND)
        ));
        assertThatThrownBy(() -> cards.addAll(List.of(new Card(CardNumber.EIGHT, CardShape.DIAMOND)))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
