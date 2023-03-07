package blackjack.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.deck.CardsGenerator;
import blackjack.domain.deck.Deck;
import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("카드를 여러 장 뽑아 줄 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    void drawCards(int size) {
        List<Card> cards = deck.drawCards(size);
        assertThat(cards).hasSize(size);
    }
}
