package blackjack.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeckTest {

    @Test
    @DisplayName("덱을 생성한다.")
    void constructorDeckTest() {
        List<Card> cards = List.of();

        Assertions.assertThatNoException().isThrownBy(() -> new Deck(cards));
    }
}
