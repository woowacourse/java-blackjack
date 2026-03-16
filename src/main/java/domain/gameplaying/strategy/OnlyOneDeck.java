package domain.gameplaying.strategy;

import domain.gameplaying.BlackJackDeck;
import domain.gameplaying.CardMark;
import domain.gameplaying.CardRank;
import domain.gameplaying.Card;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class OnlyOneDeck implements BlackJackDeck {

    private final Deque<Card> cards;

    public OnlyOneDeck() {
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
