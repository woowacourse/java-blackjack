package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public final class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void receiveCards(final Deck deck, final int numberOfCardsToReceive) {
        for (int i = 0; i < numberOfCardsToReceive; i++) {
            receiveToPlayers(deck);
            receiveToDealer(deck);
        }
    }

    private void receiveToPlayers(final Deck deck) {
        for (Player player : players.getPlayers()) {
            Card drawnCard = deck.drawCard();
            player.receiveCard(drawnCard);
        }
    }

    private void receiveToDealer(final Deck deck) {
        Card drawnCard = deck.drawCard();
        dealer.receiveCard(drawnCard);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
