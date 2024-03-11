package player;

import card.Card;
import card.CardDeck;
import cardGame.GameParticipantCards;
import java.util.List;

public class Player extends GameParticipantCards {

    private final Name name;

    private Player(List<Card> cardDeck, Name name) {
        super(cardDeck);
        this.name = name;
    }

    public static Player joinGame(String name, CardDeck cardDeck) {
        return new Player(cardDeck.firstCardSettings(), new Name(name));
    }

    public Name getName() {
        return name;
    }

    public boolean isWinner(int dealerScore) {
        if (isBust()) {
            return false;
        }
        return dealerScore < getCardScore();
    }
}
