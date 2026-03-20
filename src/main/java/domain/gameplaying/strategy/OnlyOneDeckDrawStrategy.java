package domain.gameplaying.strategy;

import domain.gameplaying.DrawStrategy;
import domain.gameplaying.CardMark;
import domain.gameplaying.CardRank;
import domain.gameplaying.Card;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class OnlyOneDeckDrawStrategy implements DrawStrategy {

    private final Deque<Card> cards;

    public OnlyOneDeckDrawStrategy() {
        this.cards = mixedCards();
    }

    @Override
    public Card draw() {
        requireCardsRemaining();
        return cards.poll();
    }

    private Deque<Card> mixedCards() {
        List<Card> sortedCards = new ArrayList<>(getAllCards());
        Collections.shuffle(sortedCards);

        return new ArrayDeque<>(sortedCards);
    }

    private List<Card> getAllCards() {
        return Arrays.stream(CardMark.values())
                .flatMap(mark -> allCardRanksWith(mark).stream())
                .toList();
    }

    private List<Card> allCardRanksWith(CardMark mark) {
        return Arrays.stream(CardRank.values())
                .map(cardRank -> new Card(cardRank, mark))
                .toList();
    }

    private void requireCardsRemaining() {
        if(cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 남아있는 카드가 없습니다.");
        }
    }
}
