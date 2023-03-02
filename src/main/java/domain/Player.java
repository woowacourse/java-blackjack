package domain;

public class Player {

    private Name playerName;
    private CardPool cardPool;

    public Player(String playerName, CardPool cardPool) {
        this.playerName = new Name(playerName);
        this.cardPool = cardPool;
    }

    public void draw(Card card) {
        cardPool.add(card);
    }

    public boolean hasSameNameWith(String name) {
        return playerName.getValue().equals(name);
    }

    public int sumCardPool() {
        return cardPool.sumCardNumbers();
    }

    public int compareTo(Player otherPlayer) {
        return sumCardPool() - otherPlayer.sumCardPool();
    }
}
