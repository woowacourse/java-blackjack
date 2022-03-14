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

    private static final String DELIMITER = ",";

    private final List<Player> players;
    private final Dealer dealer;

    public BlackjackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame create(String inputNames) {
        List<Player> players = Arrays.stream(inputNames.split(DELIMITER))
            .map(playerName -> new Player(playerName.trim()))
            .collect(Collectors.toList());
        return new BlackjackGame(players, new Dealer());
    }

    public void drawBaseCards(Deck deck) {
        for (Player player : players) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
