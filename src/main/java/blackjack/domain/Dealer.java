package blackjack.domain;

public class Dealer extends Participant {

    private static final int CAN_HIT_LIMIT = 17;
    private static final int NO_BENEFIT = 0;

    private final BetManager betManager = new BetManager();

    public void addPlayerBetMoney(Player player, int betMoney) {
        betManager.add(player, betMoney);
    }

    public GameResult judgeGameResult(Players players) {
        GameResult gameResult = new GameResult();
        players.getPlayers().forEach(player -> gameResult.addResult(player, getPlayerBenefit(player)));

        return gameResult;
    }

    private int getPlayerBenefit(Player player) {
        int betMoney = betManager.getBetMoney(player);

        if (player.isBust()) {
            return -betMoney;
        }

        if (isBust()) {
            return betMoney;
        }

        return compareSum(player, betMoney);
    }

    private int compareSum(Player player, int betMoney) {
        if (player.getSum() == getSum()) {
            return NO_BENEFIT;
        }

        if (player.getSum() > getSum()) {
            return betMoney;
        }

        return -betMoney;
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < CAN_HIT_LIMIT;
    }
}
