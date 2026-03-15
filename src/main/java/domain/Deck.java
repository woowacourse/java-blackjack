package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import vo.Rank;
import vo.Suit;

public class Deck {
    private final ShuffleStrategy shuffleStrategy;
    private final List<Card> cards;

    public Deck(ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
        this.cards = makeCards();
    }

    public void shuffleCards() {
        shuffleStrategy.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("[ERROR] 더 이상의 카드를 꺼낼 수 없습니다.");
        }
        return cards.removeFirst();
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }
}
