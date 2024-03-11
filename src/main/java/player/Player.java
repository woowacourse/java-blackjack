package player;

import card.Card;
import card.CardDeck;
import cardGame.GameParticipantCards;
import java.util.List;
import java.util.function.BiConsumer;
import player.dto.CardsStatus;

public class Player extends GameParticipantCards {

    private final Name name;

    private Player(List<Card> cardDeck, Name name) {
        super(cardDeck);
        this.name = name;
    }

    public static Player joinGame(String name, CardDeck cardDeck) {
        return new Player(cardDeck.firstCardSettings(), new Name(name));
    }

    public CardsStatus play(BiConsumer<Player, CardDeck> playMatch, CardDeck cardDeck) {
        playMatch.accept(this, cardDeck);
        return new CardsStatus(name, getCards());
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
