package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {

    public static final int INITIAL_CARDS = 2;
    public static final int DEALER_MIN_SCORE = 16;
    public static final int BLACKJACK_SCORE = 21;

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackjackGame(CardDeck cardDeck, Dealer dealer, Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame of(CardDeck cardDeck, Dealer dealer, Players players) {
        return new BlackjackGame(cardDeck, dealer, players);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(cardDeck);
            dealer.receive(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name) {
        players.passCardByName(name, cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (dealer.canReceive()) {
            dealer.receive(cardDeck.popCard());
            return true;
        }
        return false;
    }

    public boolean checkPlayerCanReceive(String name) {
        return players.canPlayerReceive(name);
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }

    public Player getPlayerByName(String name) {
        return players.findByName(name);
    }
}
