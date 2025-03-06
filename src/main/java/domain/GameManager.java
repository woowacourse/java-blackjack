package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.List;

public class GameManager {

    private static final int INITIAL_CARDS = 2;
    public static final int DEALER_MIN_SCORE = 16;

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private GameManager(CardDeck cardDeck, Dealer dealer, Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static GameManager of(CardDeck cardDeck, Dealer dealer, Players players) {
        return new GameManager(cardDeck, dealer, players);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(cardDeck);
            dealer.receive(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name) {
        Player player = players.findByName(name);
        player.receive(cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (dealer.getScore() > DEALER_MIN_SCORE) {
            return false;
        }
        dealer.receive(cardDeck.popCard());
        return true;
    }

    public int getScoreOf(String name) {
        Player player = players.findByName(name);
        return player.getScore();
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }
}
