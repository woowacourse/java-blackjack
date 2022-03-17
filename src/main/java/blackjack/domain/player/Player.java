package blackjack.domain.player;

import blackjack.domain.betting.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

import java.util.List;

public abstract class Player {

    Cards cards;
    String name;

    Player(final String name) {
        this.cards = new Cards();
        this.name = name;
    }

    public abstract boolean acceptableCard();

    public abstract boolean isParticipant();

    public int calculateFinalScore() {
        return cards.calculateEndScore();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return name;
    }
}
