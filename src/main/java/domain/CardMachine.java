package domain;

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
        setInitialDecks(decks);
        Collections.shuffle(decks);
        this.decks = new ArrayDeque<>(decks);
    }

    private void setInitialDecks(List<Card> decks) {
        setRanksAndSuits(decks);
    }

    private void setRanksAndSuits(List<Card> decks) {
        for (Rank rank : Rank.values()) {
            setSuits(decks, rank);
        }
    }

    private void setSuits(List<Card> decks, Rank rank) {
        for (Suit suit : Suit.values()) {
            addRepeatSix(decks, rank, suit);
        }
    }

    private void addRepeatSix(List<Card> decks, Rank rank, Suit suit) {
        for (int i = 0; i < DECK_COUNT; i++) {
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
