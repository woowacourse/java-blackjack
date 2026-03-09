package domain.strategy;

import domain.DrawStrategy;
import domain.constant.CardMark;
import domain.constant.CardRank;
import domain.vo.Card;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class OneDeckStrategy implements DrawStrategy {

    private final Deque<Card> cards;

    public OneDeckStrategy() {
        this.cards = mixedCards();
    }

    @Override
    public Card draw() {
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
}
