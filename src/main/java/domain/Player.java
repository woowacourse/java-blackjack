package domain;

public class Player {

    private Name playerName;
    private CardPool cardPool;

    public Player(String playerName, CardPool cardPool) {
        this.playerName = new Name(playerName);
        this.cardPool = cardPool;
    }

    public int sumCardPool() {
        return cardPool.sumCardNumbers();
    }

    public int compareTo(Player otherPlayer) {
        return sumCardPool() - otherPlayer.sumCardPool();
    }
}
