package blackjack.domain;

import java.util.List;

public class Participants {

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

    public void handInitialCards(Deck deck) {
        handInitialCardsToDealer(deck);
        players.handInitialCards(deck);
    }

    private void handInitialCardsToDealer(Deck deck) {
        for (int i = 0; i < INITIAL_HAND_OUT_COUNT; i++) {
            dealer.take(deck.draw());
        }
    }

    public Card openDealerFirstCard() {
        return dealer.getCards().get(0);
    }

    public PlayerWinResults computePlayersWinResult() {
        PlayerWinResults playerWinResults = new PlayerWinResults();
        for (Player player : players.getPlayers()) {
            if (player.isBust()) {
                playerWinResults.addResultByPlayerName(player.getName(), WinResult.LOSE);
                continue;
            }
            playerWinResults.addResultByPlayerName(player.getName(), dealer.judge(player));
        }
        return playerWinResults;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getPlayerNames() {
        return players.getPlayersName();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
