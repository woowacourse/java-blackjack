package deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;

class CardsGeneratorTest {
    @DisplayName("생성되는 카드는 52장어야한다.")
    @Test
    void generate() {
        CardsGenerator cardsGenerator = new ShuffledCardsGenerator();
        Stack<Card> cards = cardsGenerator.generate();
        assertThat(cards.size()).isEqualTo(52);
    }
}
