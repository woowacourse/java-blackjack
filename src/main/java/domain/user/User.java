package domain.user;

import domain.CardDeck;
import domain.CardSetting;
import domain.TrumpCard;
import java.util.List;

public abstract class User {
    protected final UserInformation userInformation;
    protected final CardDeck cardDeck;

    public User(String name) {
        this.userInformation = new UserInformation(name);
        this.cardDeck = new CardDeck();
    }

    public abstract boolean isImpossibleDraw();

    public void drawCard() {
        TrumpCard trumpCard = CardSetting.drawCard();
        cardDeck.addTrumpCard(trumpCard);
        if (cardDeck.checkOverScore()) {
            userInformation.burst();
        }
    }

    public boolean has(String name) {
        return getName().equals(name);
    }

    public String getName() {
        return this.userInformation.getName();
    }

    public int getSize() {
        return cardDeck.cardsSize();
    }

    public abstract List<TrumpCard> openCard();

    public CardDeck getCardDeck() {
        return this.cardDeck;
    }

    public boolean isBurst() {
        return this.userInformation.isBurst();
    }
}
