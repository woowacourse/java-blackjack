package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards(Arrays.asList(Card.of(), Card.of()));
    }

    @Test
    @DisplayName("카드를 두장 넣으면, 사이즈가 2가 나온다.")
    void sizeTest() {
        assertThat(cards.getList().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 두장 넣고 한장 추가하면, 사이즈가 3이 나온다.")
    void addTest() {
        cards.add(Card.of());
        assertThat(cards.getList().size()).isEqualTo(3);
    }


    @Test
    @DisplayName("카드를 두장 넣고 한장 추가하면, 사이즈가 3이 나온다.")
    void addTes11t() {
        Class Card = new Class(
    }

}
