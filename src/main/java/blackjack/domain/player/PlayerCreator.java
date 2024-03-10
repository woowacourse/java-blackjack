package blackjack.domain.player;

import blackjack.domain.card.CardDeck;

public class PlayerCreator {

    private static final String DEALER_NAME = "딜러";

    private final HandCreator handCreator;

    public PlayerCreator(HandCreator handCreator) {
        this.handCreator = handCreator;
    }

    public Participant createPlayerFrom(PlayerName playerName, CardDeck cardDeck) {
        return new Participant(playerName, handCreator.createPlayerHandFrom(cardDeck));
    }

    public Participant createDealerFrom(CardDeck cardDeck) {
        return new Participant(new PlayerName(DEALER_NAME), handCreator.createDealerHandFrom(cardDeck));
    }
}
