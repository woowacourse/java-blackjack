package domain;

public class GameParticipants {
    private final Dealer dealer;
    private final Players players;

    private GameParticipants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameParticipants of(Dealer dealer, Players players) {
        return new GameParticipants(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public boolean isAllPlayersBust() {
        return players.isAllBust();
    }
}