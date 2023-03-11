package blackjack.domain.participant;

import blackjack.domain.bet.BetManager;
import blackjack.domain.bet.Money;
import blackjack.domain.card.Card;
import blackjack.domain.result.GameResult;

public class Dealer extends Participant {

    private static final int CAN_HIT_LIMIT = 17;

    private final BetManager betManager = new BetManager();

    public void addPlayerBetMoney(Player player, Money betMoney) {
        betManager.add(player, betMoney);
    }

    public GameResult judgeGameResult(Players players) {
        GameResult gameResult = new GameResult();
        players.getPlayers().forEach(player -> gameResult.addResult(player, getPlayerBenefit(player)));

        return gameResult;
    }

    private Money getPlayerBenefit(Player player) {
        Money betMoney = betManager.getBetMoney(player);

        if (player.isBust()) {
            return betMoney.getNegativeMoney();
        }

        if (isBust()) {
            return checkBlackJackBenefit(player, betMoney);
        }

        return compareSum(player, betMoney);
    }

    private Money compareSum(Player player, Money betMoney) {
        if (player.getSum() == getSum()) {
            return Money.NO_BENEFIT;
        }

        if (player.getSum() > getSum()) {
            return checkBlackJackBenefit(player, betMoney);
        }

        return betMoney.getNegativeMoney();
    }

    private Money checkBlackJackBenefit(Player player, Money betMoney) {
        if (player.isBlackJack()) {
            return betMoney.getBlackJackMoney();
        }

        return betMoney;
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < CAN_HIT_LIMIT;
    }
}
