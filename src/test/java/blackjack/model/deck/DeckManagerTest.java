package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckManagerTest {
    @DisplayName("카드 덱에서 한장을 뽑는다")
    @Test
    void drawCard() {
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.DIAMOND),
                new Card(CardNumber.FIVE, CardShape.DIAMOND)
        );
        DeckManager deckManager = new DeckManager(cards);

        deckManager.drawCard();

        assertThat(deckManager)
                .extracting("cardDeck")
                .satisfies(result -> {
                    assertThat(result).isInstanceOf(Queue.class);
                    assertThat((Queue<Card>) result).hasSize(1);
                });
    }

    @DisplayName("덱이 비어있을 경우 카드를 뽑을 수 없다")
    @Test
    void validateEmptyDeck() {
        List<Card> emptyCards = new ArrayList<>();
        DeckManager deckManager = new DeckManager(emptyCards);

        assertThatThrownBy(deckManager::drawCard).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("카드 여러장을 발급한다")
    @Test
    void drawCards() {
        DeckGenerator deckGenerator = new DeckGenerator();
        DeckManager deckManager = new DeckManager(deckGenerator.generate());

        List<Card> cards = deckManager.drawCards();

        assertThat(cards).hasSize(2);
    }
}
