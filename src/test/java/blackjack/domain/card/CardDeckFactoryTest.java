package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeckFactoryTest {
    @Test
    @DisplayName("CardDeckFactory 생성 테스트")
    void init() {
        final CardDeck cardDeck = CardDeckFactory.make(new NoShuffleStrategy());
        assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @Test
    @DisplayName("섞기 전략에 대한 테스트")
    void shuffleStrategyTest() {
        final CardDeck cardDeck = CardDeckFactory.make(new NoShuffleStrategy());
        final List<Card> cards = new LinkedList<>();
        for (CardLetter letter : CardLetter.values()) {
            Arrays.stream(CardSuit.values())
                    .map(suit -> new Card(letter, suit))
                    .forEach(cards::add);
        }

        assertEquals(cardDeck.getCards(), cards);
    }

    class NoShuffleStrategy implements ShuffleStrategy {
        @Override
        public List<Card> shuffle(List<Card> cards) {
            return cards;
        }
    }
}
