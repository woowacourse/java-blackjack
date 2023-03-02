package deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;

class CardsGeneratorTest {
    CardsGenerator cardsGenerator;

    @BeforeEach
    void setUp() {
        cardsGenerator = new ShuffledCardsGenerator();
    }

    @DisplayName("생성되는 카드는 52장어야한다.")
    @Test
    void generate() {
        Stack<Card> cards = cardsGenerator.generate();
        assertThat(cards.size()).isEqualTo(52);
    }

    @DisplayName("중복인 카드가 생성되지 않는다.")
    @Test
    void generateUnique() {
        Stack<Card> cards = cardsGenerator.generate();
        assertThat(cards.stream().distinct()).hasSize(52);
    }
}
