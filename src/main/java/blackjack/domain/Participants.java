package blackjack.domain;

import java.util.List;

public class Participants {

    private final Dealer dealer = new Dealer();
    private final Players players;

    private Participants(Players players) {
        this.players = players;
    }

    public static Participants of(List<String> playersName) {
        Players players = Players.from(playersName);
        return new Participants(players);
    }

    public void handInitialCards(Deck deck) {
        dealer.handInitialCards(deck);
        players.handInitialCards(deck);
    }

    public GameResult getGameResult() {
        return dealer.judgeGameResult(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getPlayersName() {
        return players.getPlayersName();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
