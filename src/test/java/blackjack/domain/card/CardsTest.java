package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(Arrays.asList(new Card(Suit.하트, Face.ACE), new Card(Suit.클로버, Face.ACE)));
    }

    @Test
    @DisplayName("카드를 두장 넣으면, 사이즈가 2가 나온다.")
    void size() {
        assertThat(cards.getList().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 두장 넣고 한장 추가하면, 사이즈가 3이 나온다.")
    void add() {
        cards.add(new Card(Suit.클로버, Face.NINE));

        assertThat(cards.getList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("카드 A A 를 뽑으면, 12점이 나온다.")
    void calculateCardDoubleA() {
        assertThat(cards.getScore()).isEqualTo(12);
        assertThat(cards.getScore()).isNotEqualTo(13);
    }

    @Test
    @DisplayName("22점이 나오는경우, Bust가 발생한다.")
    void bust() {
        cards.add(new Card(Suit.다이아몬드, Face.JACK));
        cards.add(new Card(Suit.다이아몬드, Face.KING));

        assertThat(cards.isBust()).isTrue();
    }
}
