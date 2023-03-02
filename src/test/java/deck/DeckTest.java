package deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardNumber;
import card.Pattern;
import fixedCaradsGenerator.FixedCardsGenerator;

class DeckTest {
    Deck deck;

    @BeforeEach
    void setUp() {
        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator();
        deck = new Deck(fixedCardsGenerator);
    }

    @DisplayName("Deck을 생성할 수 있다.")
    @Test
    void create() {
        CardsGenerator fixedCardsGenerator = new FixedCardsGenerator();
        assertThatCode(() -> new Deck(fixedCardsGenerator))
                .doesNotThrowAnyException();
    }

    @DisplayName("맨 위의 카드를 뽑아줄 수 있다.")
    @Test
    void drawCard() {
        Card card = deck.drawCard();
        assertThat(card).isEqualTo(new Card(CardNumber.ACE, Pattern.SPADE));
    }
}
