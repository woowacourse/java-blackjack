package domain.gamer;

import domain.card.Card;
import domain.card.CardBundle;

import java.util.List;

public abstract class Gamer {

    protected final PlayerName name;
    protected CardBundle cardBundle;

    public Gamer(PlayerName name) {
        this.name = name;
        this.cardBundle = CardBundle.empty();
    }

    public String getMyName() {
        return name.name();
    }

    public int getResultScore() {
        return cardBundle.getResultScore();
    }

    public CardBundle getCardBundle() {
        return CardBundle.from(cardBundle.openMyCards());
    }

    public List<Card> openMyCards() {
        return cardBundle.openMyCards();
    }

    public boolean isBlackjack() {
        return cardBundle.isBlackjack();
    }

    public boolean isBusted() {
        return cardBundle.isBusted();
    }

    public boolean isStand() {
        return cardBundle.isStand();
    }

}
