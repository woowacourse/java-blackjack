package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.PlayersResult;
import java.util.Map;

public class BlackJackResultManager {

    private final PlayersResult playersResult;
    private final DealerResult dealerResult;

    public BlackJackResultManager() {
        this.playersResult = PlayersResult.create();
        this.dealerResult = DealerResult.create();
    }

    public void calculateCardResult(Players players, Dealer dealer, GameRuleEvaluator gameRuleEvaluator) {
        for (Player player : players.getPlayers()) {
            saveResult(dealer, gameRuleEvaluator, player);
        }
    }

    private void saveResult(Dealer dealer, GameRuleEvaluator gameRuleEvaluator, Player player) {
        boolean isBustedDealer = gameRuleEvaluator.isBustedFor(dealer.getCardHolder());
        boolean isBustedPlayer = gameRuleEvaluator.isBustedFor(player.getCardHolder());

        if (isBustedDealer) {
            processWhenDealerIsBusted(player, isBustedPlayer);
            return;
        }

        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.LOSE);
            return;
        }

        GameResultType resultOfPlayer = decideResultOfPlayer(player, dealer);
        saveResultWithPlayerResult(player, resultOfPlayer);
    }

    private void processWhenDealerIsBusted(Player player, boolean isBustedPlayer) {
        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.TIE);
            return;
        }

        saveResultWithPlayerResult(player, GameResultType.WIN);
    }

    public GameResultType decideResultOfPlayer(Player player, Dealer dealer) {
        int playerValue = player.getCardHolder().getOptimisticValue();
        int dealerValue = dealer.getCardHolder().getOptimisticValue();

        return GameResultType.find(playerValue, dealerValue);
    }

    public void saveResultWithPlayerResult(Player player, GameResultType gameResultOfPlayer) {
        GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

        playersResult.save(player, gameResultOfPlayer);
        dealerResult.addCountOf(gameResultOfDealer);
    }

    public Map<Player, GameResultType> getPlayersResult() {
        return playersResult.getAllResult();
    }

    public Map<GameResultType, Integer> getDealerResult() {
        return dealerResult.getDealerResult();
    }
}
