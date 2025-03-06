package model;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private final Dealer dealer;
    private final Players players;
    private final CardDeck deck = new CardDeck();

    public GameManager(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void divideAllParticipant(int amount) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            divideCardByParticipant(participant, amount);
        }
    }

    public void divideCardByParticipant(Participant participant, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        participant.addCards(pickCards);
    }

    public void calculateVictory() {
        for (Player player : players.getPlayers()) {

        }
    }
}
