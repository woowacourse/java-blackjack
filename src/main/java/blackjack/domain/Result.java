package blackjack.domain;

import java.util.Map;

public class Result {

    private final DealerResult dealerResult;
    private final PlayersResult playersResult;

    public Result() {
        this.dealerResult = new DealerResult();
        this.playersResult = new PlayersResult();
    }

    public void save(Player player, GameResultType gameResultType) {
        GameResultType gameResultTypeOfDealer = gameResultType.getOppositeType();

        playersResult.save(player, gameResultType);
        dealerResult.addCountOf(gameResultTypeOfDealer);
    }

    public Map<GameResultType, Integer> getDealerResult() {
        return dealerResult.getDealerResult();
    }

    public Map<Player, GameResultType> getPlayersResult() {
        return playersResult.getAllResult();
    }
}
