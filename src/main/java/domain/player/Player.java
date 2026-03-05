package domain.player;

import domain.card.Card;
import domain.card.CardBundle;

public class Player {

    private PlayerName name;
    private CardBundle cardBundle;

    private Player(String name) {
        this.cardBundle = CardBundle.empty();
        this.name = PlayerName.from(name);
    }

    public static Player from(String name) {
        return new Player(name);
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

    public String toDisplay() {
        return name.name();
    }

}
