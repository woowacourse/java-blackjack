package domain.user;

import domain.card.Card;

public class Player {

    private static final int DEFAULT_BET_AMOUNT = 0;

    private final PlayerName playerName;
    private final CardPool cardPool;
    private final Bet bet;

    public Player(final String playerName, final CardPool cardPool) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
        this.bet = new Bet(DEFAULT_BET_AMOUNT);
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

    public void takeRevenueFrom(final Player otherPlayer) {
        bet.takeRevenueFrom(otherPlayer.bet);
    }
}
