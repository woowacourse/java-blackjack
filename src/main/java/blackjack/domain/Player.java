package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final List<Card> receivedCards = new ArrayList<>();

    public Player(PlayerName playerName) {
        this.playerName = playerName;
    }

    public void receiveCard(Card card) {
        receivedCards.add(card);
    }

    public List<Card> getReceivedCards() {
        return this.receivedCards;
    }
}
