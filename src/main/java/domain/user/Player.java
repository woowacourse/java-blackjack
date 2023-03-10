package domain.user;

import domain.card.Card;

public class Player {

    private final PlayerName playerName;
    private final CardPool cardPool;
    private final Bet bet;

    public Player(final String playerName, final CardPool cardPool, final int betAmount) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
        this.bet = new Bet(betAmount);
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

    public boolean isBlackjack() {
        return cardPool.isSumSameAsLimit();
    }

    public void takeRevenueFrom(final Player otherPlayer) {
        if (isBlackjack()) {
            bet.takeBonusRevenueFrom(otherPlayer.bet);
            return;
        }
        bet.takeRevenueFrom(otherPlayer.bet);
    }

    public void addBetAmount(int amount) {
        this.bet.addAmount(amount);
    }

    public int getRevenue() {
        return bet.getRevenue();
    }
}
