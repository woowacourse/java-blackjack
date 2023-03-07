package domain;

import domain.deck.Card;
import domain.deck.CardDeck;
import domain.deck.CardNumber;
import domain.deck.CardPattern;
import domain.generator.CardGenerator;
import domain.strategy.NoShuffleCardsStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDeckTest {
    @Test
    @DisplayName("카드덱을 생성한다.")
    void createCardDeck() {
        CardGenerator generator = new CardGenerator();

        Assertions.assertDoesNotThrow(
                () -> new CardDeck(generator.generate(new NoShuffleCardsStrategy())));
    }

    @Test
    @DisplayName("카드덱의 맨 윗장을 뽑는다.")
    void pollCard() {
        Queue<Card> cards = new LinkedList<>();
        Card firstCard = new Card(CardNumber.ACE, CardPattern.SPADE);
        Card secondCard = new Card(CardNumber.ACE, CardPattern.HEART);
        cards.add(firstCard);
        cards.add(secondCard);
        CardDeck cardDeck = new CardDeck(cards);

        assertThat(cardDeck.poll()).isEqualTo(firstCard);
    }
}
