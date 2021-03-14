package blackjack.domain.gametable;

import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.CardDeck;

public class GameTable {
    private final Participant dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public GameTable(Participant dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
        initCards();
    }

    private void initCards() {
        dealer.takeCard(cardDeck.pop());
        dealer.takeCard(cardDeck.pop());

        players.takeTwoCards(cardDeck);
    }

    public void giveCard(Participant participant) {
        if (participant.isAbleToTake()) {
            participant.takeCard(cardDeck.pop());
        }
    }

}
