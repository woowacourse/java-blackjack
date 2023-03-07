package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private static final int NUMBER_OF_DEALER = 1;
    private static final int INITIAL_HAND_OUT_COUNT = 2;

    private final Dealer dealer = new Dealer();
    private final Players players;

    private Participants(Players players) {
        this.players = players;
    }

    public static Participants of(List<String> playerNames) {
        Players players = Players.from(playerNames);
        return new Participants(players);
    }

    public int getNeededNumberOfCards() {
        return INITIAL_HAND_OUT_COUNT * getNumberOfParticipants();
    }

    private int getNumberOfParticipants() {
        return NUMBER_OF_DEALER + players.getNumberOfPlayers();
    }

    public void handInitialCards(Deck deck) {
        handInitialCards(dealer, deck);
        players.handInitialCards(deck);
    }

    private void handInitialCards(Participant participant, Deck deck) {
        for (int i = 0; i < INITIAL_HAND_OUT_COUNT; i++) {
            participant.take(deck.draw());
        }
    }

    public GameResult openDealerGameResult() {
        return GameResult.from(dealer);
    }

    public GameResult openPlayerGameResult(String playerName) {
        Player player = players.findPlayerByName(playerName);
        return GameResult.from(player);
    }

    public Card openDealerFirstCard() {
        return dealer.getCards().get(0);
    }

    public PlayerWinResults computePlayerWinResults() {
        return players.computePlayerWinResults(dealer);
    }

    public List<String> findCanDrawPlayerNames() {
        return players.findCanDrawPlayerNames();
    }

    public List<Card> getDealerCards() {
        return new ArrayList<>(dealer.getCards());
    }

    public List<Card> getPlayerCards(String playerName) {
        return players.getPlayerCards(playerName);
    }

    public Player findPlayerByName(String playerName) {
        return players.findPlayerByName(playerName);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }
}
