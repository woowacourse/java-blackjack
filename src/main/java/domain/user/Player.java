package domain.user;

import domain.card.Card;

public class Player {

    private static final int INITIAL_BET_AMOUNT = 0;

    // TODO: 소지품 객체 생성
    private final PlayerName playerName;
    private final CardPool cardPool;
    private final Bet bet;

    protected Player(ReservedRole role, final CardPool cardPool) {
        this.playerName = new PlayerName(role);
        this.cardPool = cardPool;
        this.bet = new Bet(INITIAL_BET_AMOUNT);
    }

    public Player(final String playerName, final CardPool cardPool) {
        this.playerName = new PlayerName(playerName);
        this.cardPool = cardPool;
        this.bet = new Bet(INITIAL_BET_AMOUNT);
    }

    public void draw(final Card card) {
        cardPool.add(card);
    }

    public void addAmount(int amount) {
        bet.addAmount(amount);
    }

    public int sumCardPool() {
        return cardPool.calculateTotalPoint();
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
        return cardPool.isTotalPointExceedLimit();
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
