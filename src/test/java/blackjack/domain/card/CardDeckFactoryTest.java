package blackjack.domain.card;

import blackjack.domain.card.shuffle.RandomShuffleStrategy;
import blackjack.domain.card.shuffle.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeckFactoryTest {
    @Test
    @DisplayName("CardDeckFactory의 make메서드를 호출하면, 카드덱 인스턴스가 생성된다.")
    void init() {
        final CardDeck cardDeck = CardDeckFactory.make(new NoShuffleStrategy());
        assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @Test
    @DisplayName("랜덤으로 섞은 카드덱을 반환하면, 그 안의 카드들이 모두 고유한지 검사한다.")
    void randomShuffleStrategyTest() {
        final CardDeck cardDeck = CardDeckFactory.make(new RandomShuffleStrategy());
        assertThat(cardDeck.getCards()).doesNotHaveDuplicates();
    }

    @Test
    @DisplayName("섞기 전략에 따라서 카드덱을 만들어 낸 후, 그 안에 요소들을 검사한다")
    void shuffleStrategyTest() {
        final CardDeck cardDeck = CardDeckFactory.make(new NoShuffleStrategy());
        final List<Card> cardInDeck = cardDeck.getCards();

        final List<Card> cards = new LinkedList<>();
        for (CardLetter letter : CardLetter.values()) {
            Arrays.stream(CardSuit.values())
                    .map(suit -> new Card(letter, suit))
                    .forEach(cards::add);
        }

        assertArrayEquals(cardInDeck.toArray(), cards.toArray());
    }

    class NoShuffleStrategy implements ShuffleStrategy {
        @Override
        public List<Card> shuffle(List<Card> cards) {
            return cards;
        }
    }
}
