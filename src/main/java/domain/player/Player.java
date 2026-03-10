package domain.player;

import domain.card.Card;
import domain.card.CardBundle;

import java.util.List;

public class Player {

    private final PlayerName name;
    private final CardBundle cardBundle;

    private Player(PlayerName name) {
        this.cardBundle = CardBundle.empty();
        this.name = name;
    }

    public static Player from(PlayerName name) {
        return new Player(name);
    }

    public CardBundle addCardBundle(CardBundle newCardBundle) {
        return cardBundle.addUp(newCardBundle);
    }

    public boolean hasCard(Card targetCard) {
        return cardBundle.checkExist(targetCard);
    }

    public int getResultScore() {
        return cardBundle.getResultScore();
    }

    public boolean isBusted() {
        return cardBundle.isBusted();
    }

    public String toDisplayMyName() {
        return name.name();
    }

    public List<String> disPlayMyCardBundle() {
        return cardBundle.toDisplay();
    }

}
