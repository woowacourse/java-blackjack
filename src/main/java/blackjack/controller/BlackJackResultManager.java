package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.GameResultType;
import blackjack.domain.GameResultTypeJudgement;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import blackjack.dto.ResultDto;

public class BlackJackResultManager {

    public ResultDto calculateCardResult(Players players, Dealer dealer) {
        Result result = new Result();

        for (Player player : players.getPlayers()) {
            saveResult(dealer, player, result);
        }

        return new ResultDto(result.getPlayersResult(), result.getDealerResult());
    }

    private void saveResult(Dealer dealer, Player player, Result result) {
        GameResultType gameResultType = GameResultTypeJudgement.findForPlayer(player, dealer);
        result.save(player, gameResultType);
    }
}
