package model;

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
    private final CardDeck deck;

    public GameManager(Dealer dealer, Players players, CardDeck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public void divideInitialCardToParticipant() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        for (Participant participant : participants) {
            divideCardsByParticipant(participant, INITIAL_DEAL_COUNT);
        }
    }

    public void divideCardsByParticipant(Participant participant, int amount) {
        for (int i = 0; i < amount; i++) {
            divideCardByParticipant(participant);
        }
    }

    public void divideCardByParticipant(Participant participant) {
        participant.addCard(deck.pickCard());
    }

    public void calculateVictory() {
        for (Player player : players.getPlayers()) {
            ResultType resultType = dealer.compareTo(player);
            List<MatchType> matches = resultType.getMatches();
            updateDealerResult(matches);
            updatePlayerResult(player, matches);
        }
    }

    private static void updatePlayerResult(Player player, List<MatchType> matches) {
        player.updateResult(matches.getLast());
    }

    private void updateDealerResult(List<MatchType> matches) {
        dealer.updateResult(matches.getFirst());
    }
}
