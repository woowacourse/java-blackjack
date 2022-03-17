package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import java.util.HashMap;
import java.util.Map;

public class Statistic {

    private final Map<Gambler, GameResult> gamblerResult;

    private Statistic(Dealer dealer, Gamblers gamblers) {
        this.gamblerResult = calculate(dealer, gamblers);
    }

    public static Statistic of(Dealer dealer, Gamblers gamblers) {
        return new Statistic(dealer, gamblers);
    }

    public int getCountByGameResult(GameResult inputGameResult) {
        return (int) gamblerResult.values().stream()
            .filter(gameResult -> gameResult.equals(inputGameResult))
            .count();
    }

    public GameResult getGameResultByGambler(Gambler gambler) {
        return gamblerResult.get(gambler);
    }

    private Map<Gambler, GameResult> calculate(Dealer dealer, Gamblers gamblers) {
        if (dealer.isBust()) {
            return calculateDealerBurst(gamblers);
        }
        return calculateDealerNotBurst(dealer, gamblers);
    }

    private Map<Gambler, GameResult> calculateDealerBurst(Gamblers gamblers) {
        Map<Gambler, GameResult> playerResult = new HashMap<>();
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult gameResult = getResultAtBurst(gambler);
            playerResult.put(gambler, gameResult);
        }
        return playerResult;
    }

    private GameResult getResultAtBurst(Gambler gambler) {
        if (!gambler.isBust()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    private Map<Gambler, GameResult> calculateDealerNotBurst(Dealer dealer, Gamblers gamblers) {
        Map<Gambler, GameResult> gamblerResult = new HashMap<>();
        int dealerPoint = dealer.getPoint();
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult gameResult = getResultAtNotBurst(dealerPoint, gambler);
            gamblerResult.put(gambler, gameResult);
        }
        return gamblerResult;
    }

    private GameResult getResultAtNotBurst(int dealerPoint, Gambler gambler) {
        int gamblerPoint = gambler.getPoint();
        if (gambler.isBust() || dealerPoint > gamblerPoint) {
            return GameResult.LOSE;
        }
        if (dealerPoint == gamblerPoint) {
            return GameResult.DRAW;
        }
        return GameResult.WIN;
    }
}
