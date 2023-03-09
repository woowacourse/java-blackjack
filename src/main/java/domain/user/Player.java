package domain.user;

import domain.card.Card;

public class Player {

    private static final int DEFAULT_BET_AMOUNT = 0;

    private final PlayerName playerName;
    private final CardPool cardPool;
    private final BetAmount betAmount;

    public Player(final String playerName, final CardPool cardPool) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
        this.betAmount = new BetAmount(DEFAULT_BET_AMOUNT);
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

    public PlayerName getPlayerName() {
        return playerName;
    }

    public CardPool getCardPool() {
        return cardPool;
    }
}
