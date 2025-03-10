package model;

import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import model.score.MatchType;
import model.score.ResultType;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static final int INITIAL_DEAL_COUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final CardDeck deck = new CardDeck();

    public GameManager(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void shuffle() {
        deck.shuffle();
    }

    public void divideAllParticipant() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            divideCardByParticipant(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void  divideCardByParticipant(Participant participant, int amount) {
        List<Card> pickCards = deck.pickCard(amount);
        participant.addCards(pickCards);
    }

    public void calculateVictory() {
        for (Player player : players.getPlayers()) {
            ResultType resultType = dealer.compareTo(player);
            List<MatchType> matches = resultType.getMatches();
            dealer.updateResult(matches.getFirst());
            player.updateResult(matches.getLast());
        }
    }
}
