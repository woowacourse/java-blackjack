package domain;

public class Participants {
    private Players players;
    private Dealer dealer;

    public Participants(String playerNames) {
        players = new Players(playerNames);
        dealer = new Dealer();
    }

    public Players getPlayers() {
        return players;
    }
    public Dealer getDealer() {
        return dealer;
    }

}
