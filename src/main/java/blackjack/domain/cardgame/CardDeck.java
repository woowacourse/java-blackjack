package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private static final int INITIAL_CARD_NUMBER = 2;

    private final Deque<Card> deck;

    private CardDeck(Deque<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck createShuffledDeck() {
        final List<Card> allKindOfCards = Arrays.stream(CardShape.values())
                .flatMap(CardDeck::createEachNumber)
                .collect(Collectors.toList());

        Collections.shuffle(allKindOfCards);

        return new CardDeck(new LinkedList<>(allKindOfCards));
    }

    private static Stream<Card> createEachNumber(final CardShape cardShape) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardNumber, cardShape));
    }

    public void giveInitialCards(Player player) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            giveCard(player);
        }
    }

    public void giveCard(final Player player) {
        player.addCard(this.draw());
    }

    private Card draw() {
        if (deck.size() == 0) {
            throw new IllegalStateException("카드가 존재하지 않습니다.");
        }

        return deck.pop();
    }
}
