package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Gambler;
import blackjack.domain.human.Gamblers;
import java.util.LinkedHashMap;

public class Statistic {

    private LinkedHashMap<Gambler, GameResult> gamblerResult;

    private Statistic(Dealer dealer, Gamblers gamblers) {
        this.gamblerResult = new LinkedHashMap<>();
        calculate(gamblerResult, dealer, gamblers);
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

    private void calculate(LinkedHashMap<Gambler, GameResult> gamblerResult, Dealer dealer,
        Gamblers gamblers) {
        if (dealer.isOverThanMaxPoint()) {
            calculateDealerBurst(gamblerResult, gamblers);
            return;
        }
        calculateDealerNotBurst(gamblerResult, dealer, gamblers);
    }

    private void calculateDealerBurst(LinkedHashMap<Gambler, GameResult> playerResult,
        Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult gameResult = getResultAtBurst(gambler);
            playerResult.put(gambler, gameResult);
        }
    }

    private GameResult getResultAtBurst(Gambler gambler) {
        if (!gambler.isOverThanMaxPoint()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    private void calculateDealerNotBurst(LinkedHashMap<Gambler, GameResult> gamblerResult,
        Dealer dealer, Gamblers gamblers) {
        int dealerPoint = dealer.getPoint();
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult gameResult = getResultAtNotBurst(dealerPoint, gambler);
            gamblerResult.put(gambler, gameResult);
        }
    }

    private GameResult getResultAtNotBurst(int dealerPoint, Gambler gambler) {
        int gamblerPoint = gambler.getPoint();
        if (gambler.isOverThanMaxPoint() || dealerPoint > gamblerPoint) {
            return GameResult.LOSE;
        }
        if (dealerPoint == gamblerPoint) {
            return GameResult.DRAW;
        }
        return GameResult.WIN;
    }
}
