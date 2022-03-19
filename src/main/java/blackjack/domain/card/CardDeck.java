package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CardDeck implements CardStack {

    private static final String EMPTY_CARD_DECK_EXCEPTION_MESSAGE = "카드가 모두 소진되었습니다!";

    private final LinkedList<Card> cards;

    public CardDeck() {
        this.cards = getAllCards();
        Collections.shuffle(cards);
    }

    private LinkedList<Card> getAllCards() {
        LinkedList<Card> cards = new LinkedList<>();
        Arrays.stream(CardRank.values())
                .map(this::initAllCardsOfRank)
                .forEach(cards::addAll);

        return cards;
    }

    private List<Card> initAllCardsOfRank(final CardRank rank) {
        return Arrays.stream(CardSymbol.values())
                .map(symbol -> Card.of(rank, symbol))
                .collect(Collectors.toList());
    }

    @Override
    public Card pop() {
        try {
            return cards.pop();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(EMPTY_CARD_DECK_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "CardDeck{" + "cards=" + cards + '}';
    }
}
