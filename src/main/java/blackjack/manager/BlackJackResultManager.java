package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.PlayersResult;
import blackjack.dto.ResultDto;

public class BlackJackResultManager {

    public ResultDto calculateCardResult(Players players, Dealer dealer) {
        PlayersResult playersResult = new PlayersResult();
        DealerResult dealerResult = new DealerResult();

        for (Player player : players.getPlayers()) {
            saveResult(dealer, player, playersResult, dealerResult);
        }

        return new ResultDto(playersResult.getAllResult(), dealerResult.getDealerResult());
    }

    private void saveResult(Dealer dealer, Player player, PlayersResult playersResult, DealerResult dealerResult) {
        boolean isBustedDealer = dealer.isBusted();
        boolean isBustedPlayer = player.isBusted();

        if (isBustedDealer) {
            processWhenDealerIsBusted(player, isBustedPlayer, playersResult, dealerResult);
            return;
        }

        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.LOSE, playersResult, dealerResult);
            return;
        }

        GameResultType resultOfPlayer = decideResultOfPlayer(player, dealer);
        saveResultWithPlayerResult(player, resultOfPlayer, playersResult, dealerResult);
    }

    private void processWhenDealerIsBusted(Player player, boolean isBustedPlayer, PlayersResult playersResult,
                                           DealerResult dealerResult) {
        if (isBustedPlayer) {
            saveResultWithPlayerResult(player, GameResultType.TIE, playersResult, dealerResult);
            return;
        }

        saveResultWithPlayerResult(player, GameResultType.WIN, playersResult, dealerResult);
    }

    private GameResultType decideResultOfPlayer(Player player, Dealer dealer) {
        int playerValue = player.getOptimisticValue();
        int dealerValue = dealer.getOptimisticValue();

        return GameResultType.find(playerValue, dealerValue);
    }

    public void saveResultWithPlayerResult(Player player, GameResultType gameResultOfPlayer,
                                           PlayersResult playersResult, DealerResult dealerResult) {
        GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

        playersResult.save(player, gameResultOfPlayer);
        dealerResult.addCountOf(gameResultOfDealer);
    }
}
