package domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Deck totalDeck;
    private final Dealer dealer;
    private final Players players;

    private Game(Deck totalDeck, Dealer dealer, Players players) {
        this.totalDeck = totalDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static Game registerParticipantsAndPrepareTotalDeck(List<String> playerNames,
                                                               CardShuffleStrategy strategy) {
        Deck totalDeck = Deck.createTotalDeckAndShuffle(strategy);
        Dealer dealer = new Dealer();
        Players players = Players.of(playerNames);
        return new Game(totalDeck, dealer, players);
    }

    public void readyParticipantDecks() {
        drawInitialCards(dealer, totalDeck);
        for (Player player : players) {
            drawInitialCards(player, totalDeck);
        }
    }

    private void drawInitialCards(Participant participant, Deck totalDeck) {
        for (int i = 0; i < 2; i++) {
            drawCard(participant, totalDeck);
        }
    }

    private void drawCard(Participant participant, Deck totalDeck) {
        Card card = totalDeck.drawCard();
        participant.addCard(card);
    }

    public boolean drawCardUnderCondition(Participant participant) {
        boolean isDrawable = participant.isDrawable() && totalDeck.isDrawable();
        if (isDrawable) {
            drawCard(participant, totalDeck);
        }
        return isDrawable;
    }

    public GameResult generateGameResult() {
        return GameResult.calculate(dealer, players);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        for (Player player : players) {
            participants.add(player);
        }
        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
