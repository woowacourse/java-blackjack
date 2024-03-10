package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class PlayerCreator {

    private static final String DEALER_NAME = "딜러";

    private final HandCreator handCreator;

    public PlayerCreator(HandCreator handCreator) {
        this.handCreator = handCreator;
    }

    public Player createPlayerFrom(PlayerName playerName, CardDeck cardDeck) {
        return new Player(playerName, handCreator.createPlayerHandFrom(cardDeck));
    }

    public Dealer createDealerFrom(CardDeck cardDeck) {
        return new Dealer(handCreator.createDealerHandFrom(cardDeck));
    }
}
