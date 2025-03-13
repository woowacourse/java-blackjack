package domain.participant;

import static domain.GameManager.INITIAL_CARDS;

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

    public void distributeCards(CardDeck cardDeck) {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(cardDeck);
            dealer.receive(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name, Card card) {
        Player player = players.findByName(name); // TODO: 메시지를 던지기 VS 현행
        player.receive(card);
    }


    public void passCardToDealer(Card card) {
        dealer.receive(card);
    }

    public int getScoreOf(String name) {
        Player player = players.findByName(name);
        return player.getScore();
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
