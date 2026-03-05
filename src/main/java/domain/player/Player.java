package domain.player;

import domain.card.Card;
import domain.card.CardBundle;

public class Player {

    private CardBundle cardBundle;

    private Player() {
        this.cardBundle = CardBundle.empty();
    }

    public static Player from() {
        return new Player();
    }

    public boolean hasCard(Card targetCard) {
        return cardBundle.checkExist(targetCard);
    }

    public void setUpCardBundle(CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    public void addCardBundle(CardBundle newCardBundle) {
        cardBundle.addUp(newCardBundle);
    }

//    public void addCard(Card newCard) {
//        cardBundle.addUp(newCard);
//    }

}
