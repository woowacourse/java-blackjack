package domain.card;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardTest {
    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> new Card(Denomination.ACE, Suit.CLOVER));
    }
}
