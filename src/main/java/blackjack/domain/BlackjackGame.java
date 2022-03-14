package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameScoreBoard;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final List<Participant> players;
    private final Participant dealer;

    public BlackjackGame(List<Participant> players, Participant dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame create(String inputNames) {
        List<Participant> players = Arrays.stream(inputNames.split(","))
            .map(playerName -> new Player(playerName.trim()))
            .collect(Collectors.toList());
        return new BlackjackGame(players, new Dealer());
    }

    public void drawBaseCards(Deck deck) {
        for (Participant player : players) {
            drawBaseCardsByParticipant(deck, player);
        }
        drawBaseCardsByParticipant(deck, dealer);
    }

    private void drawBaseCardsByParticipant(Deck deck, Participant participant) {
        participant.receiveCard(deck.draw());
        participant.receiveCard(deck.draw());
    }

    public boolean takeMoreCard(Participant participant, Deck deck) {
        if (participant.shouldReceive()) {
            participant.receiveCard(deck.draw());
            return true;
        }
        return false;
    }

    public GameScoreBoard calculateGameScore() {
        return GameScoreBoard.recordGameScore(dealer, players);
    }

    public List<Participant> getPlayers() {
        return players;
    }

    public Participant getDealer() {
        return dealer;
    }
}
