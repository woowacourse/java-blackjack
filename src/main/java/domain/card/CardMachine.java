package domain.card;

import exception.BlackjackException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardMachine {

    public static final String NO_EXIST_CARDS = "더 이상 카드를 뽑을 수 없습니다.";
    private static final int DECK_COUNT = 6;

    private final Deque<Card> decks;

    public CardMachine() {
        List<Card> decks = new ArrayList<>();
        makeShoe(decks);
        Collections.shuffle(decks);
        this.decks = new ArrayDeque<>(decks);
    }

    private void makeShoe(List<Card> decks) {
        for (int i = 0; i < DECK_COUNT; i++) {
            makeSingleDeck(decks);
        }
    }

    private void makeSingleDeck(List<Card> decks) {
        for (Rank rank : Rank.values()) {
            makeCardsForRank(decks, rank);
        }
    }

    private void makeCardsForRank(List<Card> decks, Rank rank) {
        for (Suit suit : Suit.values()) {
            decks.add(new Card(rank, suit));
        }
    }

    public Card drawCard() {
        if (isDrawFinished()) {
            throw new BlackjackException(NO_EXIST_CARDS);
        }
        return this.decks.pollFirst();
    }

    private boolean isDrawFinished() {
        return this.decks.isEmpty();
    }
}
