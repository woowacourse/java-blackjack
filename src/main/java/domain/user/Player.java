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

    public void addAmount(int amount) {
        bet.addAmount(amount);
    }

    //TODO: Player 추상클래스 분리 혹은 조합
    public void payFor(Player otherPlayer) {
        bet.payFor(otherPlayer.bet);
    }

    public int getRevenue() {
        return bet.getRevenue();
    }
}
