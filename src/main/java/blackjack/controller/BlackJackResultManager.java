package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.GameResultType;
import blackjack.domain.GameResultTypeJudgement;
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
        GameResultType gameResultType = GameResultTypeJudgement.findForPlayer(player, dealer);
        saveResultWithPlayerResult(player, gameResultType, playersResult, dealerResult);

    }

    public void saveResultWithPlayerResult(Player player, GameResultType gameResultOfPlayer,
                                           PlayersResult playersResult, DealerResult dealerResult) {
        GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

        playersResult.save(player, gameResultOfPlayer);
        dealerResult.addCountOf(gameResultOfDealer);
    }
}
