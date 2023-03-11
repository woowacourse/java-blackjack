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

    public void addAmount(int amount) {
        bet.addAmount(amount);
    }

    public int sumCardPool() {
        return cardPool.sumCardNumbers();
    }

    public void increaseRevenue() {
        if (isBlackjack()) {
            bet.addBonusRevenue();
            return;
        }
        bet.addRevenue();
    }

    public void decreaseRevenue() {
        bet.decreaseRevenue();
    }

    public void payFor(Player otherPlayer) {
        bet.payFor(otherPlayer.bet);
    }

    public boolean isBurst() {
        return cardPool.isSumExceedLimit();
    }

    public boolean isBlackjack() {
        return cardPool.isSumSameAsLimit();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public CardPool getCardPool() {
        return cardPool;
    }

    public int getRevenue() {
        return bet.getRevenue();
    }
}
