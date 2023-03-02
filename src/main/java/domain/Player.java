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

    public int sumCardPool() {
        return cardPool.sumCardNumbers();
    }

    public int compareTo(Player otherPlayer) {
        return sumCardPool() - otherPlayer.sumCardPool();
    }
}
