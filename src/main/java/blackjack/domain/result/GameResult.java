package blackjack.domain.result;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.bet.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class GameResult {

    public static final Money NO_BENEFIT = new Money(0);
    private static final double BLACK_JACK_ADDITIONAL_BENEFIT = 1.5;

    private final DealerResult dealerResult = new DealerResult();
    private final List<PlayerResult> playersResult = new ArrayList<>();

    private GameResult() {
    }

    public static GameResult of(Dealer dealer, Players players) {
        GameResult gameResult = new GameResult();
        players.getPlayers()
                .forEach(player -> gameResult.addResult(player, gameResult.getPlayerBenefit(dealer, player)));
        return gameResult;
    }

    private void addResult(Player player, Money betMoney) {
        dealerResult.add(betMoney.getNegativeMoney());
        playersResult.add(new PlayerResult(player.getName(), betMoney));
    }

    private Money getPlayerBenefit(Dealer dealer, Player player) {
        Money betMoney = player.getBetMoney();

        if (player.isBust()) {
            return betMoney.getNegativeMoney();
        }

        if (dealer.isBust()) {
            return checkBlackJackBenefit(player, betMoney);
        }

        return compareSum(dealer, player, betMoney);
    }

    private Money compareSum(Dealer dealer, Player player, Money betMoney) {
        if (player.getSum() == dealer.getSum()) {
            return NO_BENEFIT;
        }

        if (player.getSum() > dealer.getSum()) {
            return checkBlackJackBenefit(player, betMoney);
        }

        return betMoney.getNegativeMoney();
    }

    private Money checkBlackJackBenefit(Player player, Money betMoney) {
        if (player.isBlackJack()) {
            return getBlackJackMoney(betMoney);
        }

        return betMoney;
    }

    private Money getBlackJackMoney(Money betMoney) {
        return new Money((int) (BLACK_JACK_ADDITIONAL_BENEFIT * betMoney.getValue()));
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return playersResult;
    }
}
