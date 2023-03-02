package deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;

class DeckTest {
    CardsGenerator cardsGenerator;

    @BeforeEach
    void setUp() {
        cardsGenerator = new ShuffledCardsGenerator();
    }

    @DisplayName("Deck을 생성할 수 있다.")
    @Test
    void create() {
        Stack<Card> generatedCards = cardsGenerator.generate();
        assertThatCode(() -> new Deck(generatedCards))
                .doesNotThrowAnyException();
    }

    @DisplayName("맨 위의 카드를 뽑아줄 수 있다.")
    @Test
    void drawCard() {
        cardsGenerator = () -> {
            List<Card> cards = Arrays.stream(CardNumber.values())
                    .flatMap(cardNumber ->
                            Arrays.stream(Pattern.values()).map(pattern -> new Card(cardNumber, pattern))
                    ).collect(Collectors.toList());
            Stack<Card> cardStack = new Stack<>();
            cardStack.addAll(cards);
            return cardStack;
        };
        Deck deck = new Deck(cardsGenerator.generate());
        Card card = deck.drawCard();
        assertThat(card).isEqualTo(new Card(CardNumber.KING, Pattern.DIAMOND));
    }
}
