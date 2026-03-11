package domain.game_playing.strategy;

import domain.game_playing.DrawStrategy;
import domain.game_playing.CardMark;
import domain.game_playing.CardRank;
import domain.game_playing.Card;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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
