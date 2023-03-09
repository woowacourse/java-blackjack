package domain.user;

import domain.card.Card;

public class Player {

    private static final int DEFAULT_BET_AMOUNT = 0;

    private final PlayerName playerName;
    private final CardPool cardPool;
    private final int betAmount;

    public Player(final String playerName, final CardPool cardPool) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
        this.betAmount = DEFAULT_BET_AMOUNT;
    }

    public Player(final String playerName, final CardPool cardPool, final int betAmount) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
        this.betAmount = betAmount;
    }

    public void draw(final Card card) {
        cardPool.add(card);
    }

    public int sumCardPool() {
        return cardPool.sumCardNumbers();
    }

    public boolean isBurst() {
        return cardPool.isSumExceedLimit();
    }

    public void addBetAmount() {

    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public CardPool getCardPool() {
        return cardPool;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
