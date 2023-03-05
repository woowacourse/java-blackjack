package domain.user;

import domain.card.Card;

public class Player {

    private PlayerName playerName;
    private CardPool cardPool;

    public Player(String playerName, CardPool cardPool) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
    }

    public void draw(Card card) {
        cardPool.add(card);
    }

    public boolean hasSameNameWith(String name) {
        return playerName.isSameWith(name);
    }

    public int sumCardPool() {
        return cardPool.sumCardNumbers();
    }

    public boolean isBurst() {
        return cardPool.isSumExceedLimit();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public CardPool getCardPool() {
        return cardPool;
    }
}
