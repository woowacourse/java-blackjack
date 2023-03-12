package blackjackgame.domain.user;

import blackjackgame.domain.Denomination;
import blackjackgame.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {
    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public void add(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void add(List<Card> receivedCards) {
        cards.addAll(receivedCards);
    }

    public int countOfAce() {
        return (int) cards.stream()
                .map(Card::getScore)
                .filter(score -> score == Denomination.ACE.getScore())
                .count();
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
