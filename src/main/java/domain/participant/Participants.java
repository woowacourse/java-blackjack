package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import java.util.Collections;
import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(Dealer dealer, Players players) {
        return new Participants(dealer, players);
    }

    public void passCardToPlayers(CardDeck cardDeck) {
        players.receiveCards(cardDeck);
    }

    public void passCardToPlayer(String name, Card card) {
        players.passCardByName(name, card);
    }

    public void passCardToDealer(Card card) {
        dealer.receive(card);
    }

    public int getScoreOfPlayer(String name) {
        return players.getScoreOf(name);
    }

    public int getScoreOfDealer() {
        return dealer.getScore();
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }

    public Player getPlayerByName(String name) {
        return players.findByName(name);
    }
}
