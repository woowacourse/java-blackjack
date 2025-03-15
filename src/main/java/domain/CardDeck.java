package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import exception.ErrorException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private final static int INITIAL_DISTRIBUTION_COUNT = 2;
    private final Deque<Card> cards;

    public CardDeck() {
        this.cards = initialize();
    }

    private Deque<Card> initialize() {
        List<Card> cardList = new ArrayList<>();
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .forEach(cardList::add);
        Collections.shuffle(cardList);
        return new ArrayDeque<>(cardList);
    }

    public List<List<Card>> pickInitialCardsStack(int stackSize) {
        List<List<Card>> cardsStack = new ArrayList<>();
        for (int i = 0; i < INITIAL_DISTRIBUTION_COUNT; i++) {
            List<Card> cards = pickInitialCards(stackSize);
            cardsStack.add(cards);
        }
        return cardsStack;
    }

    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new ErrorException("더 이상 뽑을 수 있는 카드가 존재하지 않습니다.");
        }
        return cards.pop();
    }

    private List<Card> pickInitialCards(int stackSize) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < stackSize; i++) {
            cards.add(pickCard());
        }
        return cards;
    }
}
