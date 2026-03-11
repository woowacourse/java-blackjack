package domain;

public record Participants(Dealer dealer, Players players) {

    public Player getPlayer(String name) {
        return players.getPlayer(name);
    }
}
