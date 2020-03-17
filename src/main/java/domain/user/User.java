package domain.user;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import java.util.List;

public abstract class User implements Participant {
    protected Cards cards = new Cards();

    abstract public boolean canReceiveCard();

    public void receiveFirstCards(Deck deck) {
        cards.put(deck.dealFirstCards());
    }

    public boolean isLargerThan(int score) {
        return this.cards.isLargerThan(score);
    }

    public boolean isSmallerThan(int blackjackScore) {
        return this.cards.isSmallerThan(blackjackScore);
    }

    public void receiveCard(Deck deck) {
        cards.put(deck.deal());
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getTotalScore() {
        return cards.sumScores();
    }
}
